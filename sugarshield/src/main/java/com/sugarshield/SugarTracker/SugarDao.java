package com.sugarshield.SugarTracker;

import com.sugarshield.db.DBConnection;

import java.sql.*;

public class SugarDao {

    public SugarReading insertSugarDetail(SugarReading reading) {

        // validation
        if (reading == null ||
                reading.getUserId() <= 0 ||
                reading.getSugarLevel() <= 0 ||
                reading.getReadingType() == null ||
                reading.getReadingTime() == null) {

            System.out.println("Invalid sugar reading input. Insert failed.");
            return null;
        }

        String sql = "INSERT INTO Sugar_Readings " +
                "(user_id, sugar_level, reading_type, reading_time, notes) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
             connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // set values
            statement.setInt(1, reading.getUserId());
            statement.setFloat(2, reading.getSugarLevel());
            statement.setString(3, reading.getReadingType());

            // LocalDateTime → Timestamp
            statement.setTimestamp(4,
                    java.sql.Timestamp.valueOf(reading.getReadingTime()));

            statement.setString(5, reading.getNotes());

            int rows = statement.executeUpdate();

            if (rows > 0) {

                // get generated reading_id
                try (ResultSet rs = statement.getGeneratedKeys()) {

                    if (rs.next()) {
                        reading.setReadingId(rs.getInt(1));
                    }
                }

                System.out.println("Sugar reading inserted successfully ✔");
                return reading;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


}


