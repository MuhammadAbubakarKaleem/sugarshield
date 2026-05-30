package com.sugarshield.HealthReportGenerater;

import com.sugarshield.bmiCalculator.BMICalculator;

import com.sugarshield.db.DBConnection;
import java.sql.*;


public class HealthReportDAO {

    public HealthReport generateHealthReport(int userId) {

        HealthReport report = new HealthReport();

        try (
                Connection conn = DBConnection.getConnection()
        ) {


            String sugarQuery =
                    "SELECT AVG(sugar_level) as avgSugar " +
                            "FROM Sugar_Readings " +
                            "WHERE user_id=?";

            PreparedStatement sugarStmt =
                    conn.prepareStatement(sugarQuery);

            sugarStmt.setInt(1,userId);

            ResultSet sugarRs =
                    sugarStmt.executeQuery();

            float avgSugar = 0;

            if(sugarRs.next()){
                avgSugar = sugarRs.getFloat("avgSugar");
            }



            String userQuery =
                    "SELECT height,weight FROM Users WHERE user_id=?";

            PreparedStatement userStmt =
                    conn.prepareStatement(userQuery);

            userStmt.setInt(1,userId);

            ResultSet userRs =
                    userStmt.executeQuery();

            float bmi = 0;

            if(userRs.next()){

                float height =
                        userRs.getFloat("height");

                float weight =
                        userRs.getFloat("weight");

                bmi =
                        BMICalculator.calculateBMI(weight,height);
            }

            //----------------------------------
            // Water Intake
            //----------------------------------

            String waterQuery =
                    "SELECT AVG(intake_amount) avgWater " +
                            "FROM Water_Intake WHERE user_id=?";

            PreparedStatement waterStmt =
                    conn.prepareStatement(waterQuery);

            waterStmt.setInt(1,userId);

            ResultSet waterRs =
                    waterStmt.executeQuery();

            float avgWater = 0;

            if(waterRs.next()){
                avgWater =
                        waterRs.getFloat("avgWater");
            }

            //----------------------------------
            // Sleep
            //----------------------------------

            String sleepQuery =
                    "SELECT AVG(sleep_hours) avgSleep " +
                            "FROM Sleep_Logs WHERE user_id=?";

            PreparedStatement sleepStmt =
                    conn.prepareStatement(sleepQuery);

            sleepStmt.setInt(1,userId);

            ResultSet sleepRs =
                    sleepStmt.executeQuery();

            float avgSleep = 0;

            if(sleepRs.next()){
                avgSleep =
                        sleepRs.getFloat("avgSleep");
            }

            //----------------------------------
            // Stress
            //----------------------------------

            String stressQuery =
                    "SELECT AVG(stress_level) avgStress " +
                            "FROM Stress_Logs WHERE user_id=?";

            PreparedStatement stressStmt =
                    conn.prepareStatement(stressQuery);

            stressStmt.setInt(1,userId);

            ResultSet stressRs =
                    stressStmt.executeQuery();

            int avgStress = 0;

            if(stressRs.next()){
                avgStress =
                        stressRs.getInt("avgStress");
            }

            //----------------------------------
            // Calories Burned
            //----------------------------------

            String exerciseQuery =
                    "SELECT SUM(calories_burned) totalCalories " +
                            "FROM Activity_Logs WHERE user_id=?";

            PreparedStatement exerciseStmt =
                    conn.prepareStatement(exerciseQuery);

            exerciseStmt.setInt(1,userId);

            ResultSet exerciseRs =
                    exerciseStmt.executeQuery();

            float calories = 0;

            if(exerciseRs.next()){
                calories =
                        exerciseRs.getFloat("totalCalories");
            }

            //----------------------------------
            // Report Generation
            //----------------------------------

            String status =
                    ReportGenerator
                            .determineHealthStatus(avgSugar,bmi);

            String summary = ReportGenerator.generateSummary(
                                    avgSugar,
                                    bmi,
                                    avgWater,
                                    avgSleep,
                                    avgStress,
                                    calories
                            );

            report.setUserId(userId);
            report.setAverageSugar(avgSugar);
            report.setBmi(bmi);
            report.setHealthStatus(status);
            report.setReportSummary(summary);

        }
        catch (Exception e){
            e.printStackTrace();
        }

        return report;
    }
}