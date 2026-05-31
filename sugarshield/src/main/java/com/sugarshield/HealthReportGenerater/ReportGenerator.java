package com.sugarshield.HealthReportGenerater;


public class ReportGenerator {

    public static String determineHealthStatus(float avgSugar, float bmi) {

        if (avgSugar <= 140 && bmi >= 18.5 && bmi <= 24.9) {
            return "Healthy";
        }

        if (avgSugar <= 180) {
            return "Moderate Risk";
        }

        return "High Risk";
    }

    public static String generateSummary(
            float avgSugar,
            float bmi,
            float caloriesBurned
    ) {

        StringBuilder summary = new StringBuilder();

        summary.append("Average Sugar: ").append(avgSugar).append("\n");
        summary.append("BMI: ").append(bmi).append("\n");
        summary.append("Calories Burned: ").append(caloriesBurned).append("\n");

        return summary.toString();
    }
}