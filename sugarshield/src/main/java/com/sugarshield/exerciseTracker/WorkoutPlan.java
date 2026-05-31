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

    public void generateWorkoutPlan(String category) {


        Random random = new Random();

        ArrayList<Exercise> filteredList = exerciseDao.getAllExerciseByGroupByCategory().get(category);


        // Safety check
        if (filteredList == null || filteredList.isEmpty()) {
            System.out.println("No exercises found for level!");
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

                System.out.println(" - " + e.getExerciseName()+ ".  Duration: "+ e.getDurationMinutes() +". Calories burn: "+ e.getCaloriesBurned());
            }

            System.out.println();
        }

        System.out.println("Sunday: REST DAY ");
    }
}