package com.sugarshield.HealthReportGenerater;


import java.util.HashMap;

public class HealthReport {

    private int reportId;
    private int userId;

    private String fullName;
    private int age;
    private String diabetesType;
    private float height;
    private float weight;
    private float averageSugar;
    private float bmi;
    private HashMap<String, Float> averageSugarByType;

    private String healthStatus;
    private String reportSummary;

    public HealthReport() {
        averageSugarByType = new HashMap<>();
    }

    public HealthReport(int userId,
                        float averageSugar,
                        float bmi,
                        String healthStatus,
                        String reportSummary) {

        this.userId = userId;
        this.averageSugar = averageSugar;
        this.bmi = bmi;
        this.healthStatus = healthStatus;
        this.reportSummary = reportSummary;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDiabetesType() {
        return diabetesType;
    }

    public void setDiabetesType(String diabetesType) {
        this.diabetesType = diabetesType;
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

    public float getAverageSugar() {
        return averageSugar;
    }

    public void setAverageSugar(float averageSugar) {
        this.averageSugar = averageSugar;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public HashMap<String, Float> getAverageSugarByType() {
        return averageSugarByType;
    }

    public void setAverageSugarByType(HashMap<String, Float> averageSugarByType) {
        this.averageSugarByType = averageSugarByType;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getReportSummary() {
        return reportSummary;
    }

    public void setReportSummary(String reportSummary) {
        this.reportSummary = reportSummary;
    }
}
