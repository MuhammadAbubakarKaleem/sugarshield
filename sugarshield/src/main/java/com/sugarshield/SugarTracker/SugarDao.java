package com.sugarshield.SugarTracker;

import com.sugarshield.db.DBConnection;

import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<Sugar> getSugarReadingsByUserId(int userId) {

        ArrayList<Sugar> readings = new ArrayList<>();
        String sql = "SELECT * FROM Sugar_Readings WHERE user_id = ? ORDER BY reading_time DESC";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Sugar sugar = new Sugar();
                sugar.setReadingId(resultSet.getInt("reading_id"));
                sugar.setUserId(resultSet.getInt("user_id"));
                sugar.setSugarLevel(resultSet.getFloat("sugar_level"));
                sugar.setReadingType(resultSet.getString("reading_type"));

                Timestamp timestamp = resultSet.getTimestamp("reading_time");
                if (timestamp != null) {
                    sugar.setReadingTime(timestamp.toLocalDateTime());
                }

                sugar.setNotes(resultSet.getString("notes"));
                readings.add(sugar);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return readings;
    }


}

