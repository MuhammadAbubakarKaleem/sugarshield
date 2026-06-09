package com.sugarshield.foodAnalyser;

import java.util.ArrayList;

public class FoodService {
    private final FoodDao foodDao;

    public FoodService() {
        this.foodDao = new FoodDao();
    }

    public Food searchByName(String name){
        if (name == null || name.trim().isEmpty()) {
            return null;
        }

        for (Food f : foodDao.getAllFoodAsHashMap().values()){
            if (name.trim().equalsIgnoreCase(f.getFoodName())){
                return f;
            }

        }
        return null;
    }

    public ArrayList<Food> getAllFoods() {
        return foodDao.getAllFoods();
    }
}
