package com.sugarshield.bmiCalculator;

public class BMI {

    private int userId;
    private float height; // meters
    private float weight; // kg
    private float bmi;
    private String bmiStatus;

    public BMI() {
    }

    public BMI(int userId, float height, float weight, float bmi, String bmiStatus) {
        this.userId = userId;
        this.height = height;
        this.weight = weight;
        this.bmi = bmi;
        this.bmiStatus = bmiStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public String getBmiStatus() {
        return bmiStatus;
    }

    public void setBmiStatus(String bmiStatus) {
        this.bmiStatus = bmiStatus;
    }
}