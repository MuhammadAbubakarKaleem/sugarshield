package com.sugarshield.foodAnalyser;

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
}
