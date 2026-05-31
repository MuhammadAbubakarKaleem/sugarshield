package com.sugarshield.SugarTracker;

import java.time.LocalDateTime;

public class Sugar {

    private int readingId;
    private int userId;
    private float sugarLevel;
    private String readingType;
    private LocalDateTime readingTime;
    private String notes;

    // Default constructor
    public Sugar() {
    }

    // Parameterized constructor
    public Sugar(int readingId,
                        int userId,
                        float sugarLevel,
                        String readingType,
                        LocalDateTime readingTime,
                        String notes) {

        this.readingId = readingId;
        this.userId = userId;
        this.sugarLevel = sugarLevel;
        this.readingType = readingType;
        this.readingTime = readingTime;
        this.notes = notes;
    }

    // Getters
    public int getReadingId() {
        return readingId;
    }

    public int getUserId() {
        return userId;
    }

    public float getSugarLevel() {
        return sugarLevel;
    }

    public String getReadingType() {
        return readingType;
    }

    public LocalDateTime getReadingTime() {
        return readingTime;
    }

    public String getNotes() {
        return notes;
    }

    // Setters
    public void setReadingId(int readingId) {
        this.readingId = readingId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setSugarLevel(float sugarLevel) {
        this.sugarLevel = sugarLevel;
    }

    public void setReadingType(String readingType) {
        this.readingType = readingType;
    }

    public void setReadingTime(LocalDateTime readingTime) {
        this.readingTime = readingTime;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    // toString
    @Override
    public String toString() {
        return "SugarReading{" +
                "readingId=" + readingId +
                ", userId=" + userId +
                ", sugarLevel=" + sugarLevel +
                ", readingType='" + readingType + '\'' +
                ", readingTime=" + readingTime +
                ", notes='" + notes + '\'' +
                '}';
    }
}
