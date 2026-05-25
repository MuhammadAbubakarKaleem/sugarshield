package com.sugarshield.exerciseTracker;

public class Exercise {

    private int exerciseId;
    private String exerciseName;
    private float caloriesBurned;
    private String recommendedFor;
    private int durationMinutes;
    private String difficultyLevel;


    public Exercise() {}


    public Exercise(int exerciseId,
                    String exerciseName,
                    float caloriesBurned,
                    String recommendedFor,
                    int durationMinutes,
                    String difficultyLevel) {

        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.caloriesBurned = caloriesBurned;
        this.recommendedFor = recommendedFor;
        this.durationMinutes = durationMinutes;
        this.difficultyLevel = difficultyLevel;
    }



    public int getExerciseId() {
        return exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public float getCaloriesBurned() {
        return caloriesBurned;
    }

    public String getRecommendedFor() {
        return recommendedFor;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }



    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setCaloriesBurned(float caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public void setRecommendedFor(String recommendedFor) {
        this.recommendedFor = recommendedFor;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }



    @Override
    public String toString() {

        return "Exercise ID: " + exerciseId +
                "\nExercise Name: " + exerciseName +
                "\nCalories Burned: " + caloriesBurned +
                "\nRecommended For: " + recommendedFor +
                "\nDuration Minutes: " + durationMinutes +
                "\nDifficulty Level: " + difficultyLevel;
    }
}