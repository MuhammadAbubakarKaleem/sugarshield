package com.sugarshield.SugarTracker;

import java.time.LocalDateTime;

public class SugarReading extends Sugar {

    public SugarReading createSugarReading(
            int userId,
            float sugarLevel,
            String readingType,
            LocalDateTime readingTime,
            String notes
    ) {

        SugarReading reading = new SugarReading();

        reading.setUserId(userId);
        reading.setSugarLevel(sugarLevel);
        reading.setReadingType(readingType);
        reading.setReadingTime(readingTime);
        reading.setNotes(notes);

        return reading;
    }
}