package com.sugarshield.exerciseTracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class WorkoutPlan {

    ExerciseDao exerciseDao;
    ArrayList<Exercise> exerciseList = new ArrayList<>();

    public WorkoutPlan() {
        exerciseDao = new ExerciseDao();
    }

    public HashMap<String, ArrayList<Exercise>> generateWorkoutPlan(String category) {


        Random random = new Random();
        ArrayList<Exercise> filteredList = exerciseDao.getAllExerciseByGroupByCategory().get(category);


        // Safety check
        if (filteredList == null || filteredList.isEmpty()) {
            return null ;
        }

        String[] days = {
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday"
        };

        HashMap<String, ArrayList<Exercise>> weeklyPlan = new HashMap<>();
        for (String day : days) {
            ArrayList<Exercise> dailyWorkout = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                int index = random.nextInt(filteredList.size());
                dailyWorkout.add(filteredList.get(index));
            }
            weeklyPlan.put(day, dailyWorkout);
        }
        return weeklyPlan;
    }
}