package com.sugarshield.foodAnalyser;

public class Food {

    private int    foodId;
    private String foodName;
    private float  calories;
    private float  sugarContent;
    private int    glycemicIndex;
    private float  carbohydrates;
    private String safeStatus;

    public Food() {
    }

    public int getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public float getCalories() {
        return calories;
    }

    public float getSugarContent() {
        return sugarContent;
    }

    public int getGlycemicIndex() {
        return glycemicIndex;
    }

    public float getCarbohydrates() {
        return carbohydrates;
    }

    public String getSafeStatus() {
        return safeStatus;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public void setSugarContent(float sugarContent) {
        this.sugarContent = sugarContent;
    }

    public void setGlycemicIndex(int glycemicIndex) {
        this.glycemicIndex = glycemicIndex;
    }

    public void setCarbohydrates(float carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public void setSafeStatus(String safeStatus) {
        this.safeStatus = safeStatus;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodId=" + foodId +
                ", foodName='" + foodName + '\'' +
                ", calories=" + calories +
                ", sugarContent=" + sugarContent +
                ", glycemicIndex=" + glycemicIndex +
                ", carbohydrates=" + carbohydrates +
                ", safeStatus='" + safeStatus + '\'' +
                '}';
    }
}
