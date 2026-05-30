package com.sugarshield.HealthReportGenerater;


public class HealthReport {

    private int reportId;
    private int userId;

    private float averageSugar;
    private float bmi;

    private String healthStatus;
    private String reportSummary;

    public HealthReport() {
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