package com.sugarshield.foodAnalyser;

public class FoodService {
    private final FoodDao foodDao;

    public FoodService() {
        this.foodDao = new FoodDao();
    }

    public Food searchByName(String name){
        for (Food f : foodDao.getAllFoodAsHashMap().values()){
            if (name.equalsIgnoreCase(f.getFoodName())){
                System.out.println(f.toString());
                return f;
            }

        }
        return null;
    }
}
