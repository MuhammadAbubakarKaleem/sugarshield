package com.sugarshield.foodAnalyser;

import com.sugarshield.db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;


public class FoodDao {

    public HashMap<Integer, Food> getAllFoodAsHashMap(){

        Food food ;
        HashMap<Integer, Food > foodData = new HashMap<>();
        String sql = "Select * from Food";
        try (Connection connection = DBConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet  resultSet = statement.executeQuery();
            while (resultSet.next()){
                food = new Food();
                food.setFoodId(resultSet.getInt( "food_id"));
                food.setFoodName(resultSet.getString("food_name"));
                food.setCalories(resultSet.getFloat("calories"));
                food.setSugarContent(resultSet.getFloat("sugar_content"));
                food.setGlycemicIndex(resultSet.getInt("glycemic_index"));
                food.setCarbohydrates(resultSet.getFloat("carbohydrates"));
                food.setSafeStatus(resultSet.getString("safe_status"));
                foodData.put(food.getFoodId(), food);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return foodData;
    }

    public Food getFoodByName(String foodName){
        Food food = null;
        HashMap<Integer, Food > foodData = new HashMap<>();
        String sql = "Select * from Food where food_name = ?";

        try (Connection connection = DBConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, foodName);

            ResultSet  resultSet = statement.executeQuery();
            if (resultSet.next()){
                food = new Food();
                food.setFoodId(resultSet.getInt( "food_id"));
                food.setFoodName(resultSet.getString("food_name"));
                food.setCalories(resultSet.getFloat("calories"));
                food.setSugarContent(resultSet.getFloat("sugar_content"));
                food.setGlycemicIndex(resultSet.getInt("glycemic_index"));
                food.setCarbohydrates(resultSet.getFloat("carbohydrates"));
                food.setSafeStatus(resultSet.getString("safe_status"));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return food;
    }

}