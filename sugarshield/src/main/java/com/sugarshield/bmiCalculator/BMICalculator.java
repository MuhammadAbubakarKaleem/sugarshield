package com.sugarshield.bmiCalculator;

public class BMICalculator {

    public static float calculateBMI(float weight, float height) {

        if (height <= 0 || weight <= 0) {
            throw new IllegalArgumentException("Height and Weight must be greater than 0");
        }

        return weight / (height * height);
    }

    public static float calculateBMIFromFeetInches(float weight, float heightFeetInches) {
        float heightInMeters = convertFeetInchesToMeters(String.valueOf(heightFeetInches));
        return calculateBMI(weight, heightInMeters);
    }

    public static float calculateBMIFromFeetInches(float weight, String heightFeetInches) {
        float heightInMeters = convertFeetInchesToMeters(heightFeetInches);
        return calculateBMI(weight, heightInMeters);
    }

    public static float convertFeetInchesToMeters(float heightFeetInches) {
        return convertFeetInchesToMeters(String.valueOf(heightFeetInches));
    }

    public static float convertFeetInchesToMeters(String heightFeetInches) {
        if (heightFeetInches == null || heightFeetInches.trim().isEmpty()) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }

        heightFeetInches = heightFeetInches.trim();

        String[] heightParts = heightFeetInches.split("\\.");
        int feet = Integer.parseInt(heightParts[0]);
        int inches = 0;

        if (heightParts.length > 1) {
            inches = Integer.parseInt(heightParts[1]);
        }

        if (heightParts.length > 2) {
            throw new IllegalArgumentException("Height must be like 5.9");
        }

        if (feet <= 0 && inches <= 0) {
            throw new IllegalArgumentException("Height must be greater than 0");
        }

        if (inches < 0 || inches > 11) {
            throw new IllegalArgumentException("Inches must be between 0 and 11");
        }

        int totalInches = (feet * 12) + inches;
        return totalInches * 0.0254f;
    }

    public static String getBMIStatus(float bmi) {

        if (bmi < 18.5) {
            return "Underweight";
        }
        else if (bmi < 25) {
            return "Normal";
        }
        else if (bmi < 30) {
            return "Overweight";
        }
        else {
            return "Obese";
        }
    }
}
