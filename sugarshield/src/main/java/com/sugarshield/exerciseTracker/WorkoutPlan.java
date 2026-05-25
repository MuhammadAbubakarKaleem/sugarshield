package com.sugarshield.exerciseTracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class WorkoutPlan {

    ExerciseDao exerciseDao;
    ArrayList<Exercise> exerciseList;

    public WorkoutPlan() {

        exerciseDao = new ExerciseDao();

        exerciseList = new ArrayList<>(exerciseDao.getAllExercise().values());
    }

    public void generateWorkoutPlan(String category) {

        Random random = new Random();

        ArrayList<Exercise> filteredList = new ArrayList<>();

        // Filter by category
        for (Exercise e : exerciseList) {

            if (e.getDifficultyLevel().equalsIgnoreCase(category)) {
                filteredList.add(e);
            }
        }

        // Safety check
        if (filteredList.isEmpty()) {
            System.out.println("No exercises found for category!");
            return;
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

        System.out.println("\n===== WEEKLY WORKOUT PLAN =====\n");

        for (String day : days) {

            System.out.println(day + ":");

            for (Exercise e : weeklyPlan.get(day)) {

                System.out.println(" - " + e.getExerciseName());
            }

            System.out.println();
        }

        System.out.println("Sunday: REST DAY 😴");
    }
}