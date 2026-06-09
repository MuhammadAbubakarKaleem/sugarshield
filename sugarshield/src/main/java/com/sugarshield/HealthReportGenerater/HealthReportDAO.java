package com.sugarshield.HealthReportGenerater;

import com.sugarshield.bmiCalculator.BMICalculator;

import com.sugarshield.db.DBConnection;

import java.sql.*;
import java.util.HashMap;


public class HealthReportDAO {

    public HealthReport generateHealthReport(int userId) {

        HealthReport report = new HealthReport();

        try (Connection conn = DBConnection.getConnection()) {

            // Average Sugar Level
            String sugarQuery = "SELECT AVG(sugar_level) AS avgSugar " +
                    "FROM Sugar_Readings " +
                    "WHERE user_id=?";

            PreparedStatement sugarStatement = conn.prepareStatement(sugarQuery);

            sugarStatement.setInt(1, userId);

            ResultSet sugarResultSet =
                    sugarStatement.executeQuery();

            float avgSugar = 0;

            if (sugarResultSet.next()) {
                avgSugar = sugarResultSet.getFloat("avgSugar");
            }

            // BMI Calculation

            String userQuery = "SELECT full_name, age, diabetes_type, height, weight FROM Users WHERE user_id=?";

            PreparedStatement userStatement = conn.prepareStatement(userQuery);

            userStatement.setInt(1, userId);

            ResultSet userResultSet = userStatement.executeQuery();

            float bmi = 0;

            if (userResultSet.next()) {
                String height = userResultSet.getString("height");

                float weight = userResultSet.getFloat("weight");

                report.setFullName(userResultSet.getString("full_name"));
                report.setAge(userResultSet.getInt("age"));
                report.setDiabetesType(userResultSet.getString("diabetes_type"));
                report.setHeight(userResultSet.getFloat("height"));
                report.setWeight(weight);
                bmi = BMICalculator.calculateBMIFromFeetInches(weight, height);
            }

            String sugarTypeQuery = "SELECT reading_type, AVG(sugar_level) AS avgSugar " +
                    "FROM Sugar_Readings " +
                    "WHERE user_id=? " +
                    "GROUP BY reading_type";

            PreparedStatement sugarTypeStatement = conn.prepareStatement(sugarTypeQuery);

            sugarTypeStatement.setInt(1, userId);

            ResultSet sugarTypeResultSet = sugarTypeStatement.executeQuery();

            HashMap<String, Float> averageSugarByType = new HashMap<>();

            while (sugarTypeResultSet.next()) {
                averageSugarByType.put(
                        sugarTypeResultSet.getString("reading_type"),
                        sugarTypeResultSet.getFloat("avgSugar")
                );
            }

            // Total Calories Burned
            String exerciseQuery = "SELECT SUM(calories_burned) AS totalCalories " +
                                    "FROM Activity_Logs WHERE user_id=?";

            PreparedStatement exerciseStatement = conn.prepareStatement(exerciseQuery);

            exerciseStatement.setInt(1, userId);

            ResultSet exerciseResultSet = exerciseStatement.executeQuery();

            float calories = 0;

            if (exerciseResultSet.next()) {
                calories = exerciseResultSet.getFloat("totalCalories");
            }

            // Report Generation
            String status = ReportGenerator.determineHealthStatus(avgSugar, bmi);

            String summary =
                    ReportGenerator.generateSummary(
                            avgSugar,
                            bmi,
                            calories
                    );

            report.setUserId(userId);
            report.setAverageSugar(avgSugar);
            report.setBmi(bmi);
            report.setAverageSugarByType(averageSugarByType);
            report.setHealthStatus(status);
            report.setReportSummary(summary);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return report;
    }
}
