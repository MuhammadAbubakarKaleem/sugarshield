package com.sugarshield.exerciseTracker;

import com.sugarshield.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

public class ExerciseDao {

    public HashMap<Integer, Exercise> getAllExercise() {

        Exercise exercise;
        HashMap<Integer, Exercise> exerciseData = new HashMap<>();
        String sql = "SELECT * FROM Exercise";
        try (Connection connection = DBConnection.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                exercise = new Exercise();
                exercise.setExerciseId(resultSet.getInt("exercise_id"));
                exercise.setExerciseName(resultSet.getString("exercise_name"));
                exercise.setCaloriesBurned(resultSet.getFloat("calories_burned"));
                exercise.setRecommendedFor(resultSet.getString("recommended_for"));
                exercise.setDurationMinutes(resultSet.getInt("duration_minutes"));
                exercise.setDifficultyLevel(resultSet.getString("difficulty_level"));
                exerciseData.put(exercise.getExerciseId(), exercise);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return exerciseData;
    }

}
