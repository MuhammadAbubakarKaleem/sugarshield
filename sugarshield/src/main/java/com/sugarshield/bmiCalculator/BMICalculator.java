package com.sugarshield.bmiCalculator;

public class BMICalculator {

    public static float calculateBMI(float weight, float height) {

        if (height <= 0 || weight <= 0) {
            throw new IllegalArgumentException("Height and Weight must be greater than 0");
        }

        return weight / (height * height);
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