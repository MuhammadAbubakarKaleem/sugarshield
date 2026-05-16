package com.sugarshield;

import com.sugarshield.foodAnalyser.FoodService;
import com.sugarshield.user.User;
import com.sugarshield.user.UserService;

public class App {
    private UserService userService;
    private FoodService foodService;

    public App(){
        userService = new UserService();
        foodService = new FoodService();
    }

    public static void main(String[] args) {
        System.out.println("Hello Sugar Shield");
        App sugarApp = new App();
        sugarApp.testFoodService("Bhindi Masala");


//        sugarApp.testLogin("azeem@gmail.com","123");
//        sugarApp.testLogin("abubakarkaleem22@gmail.com","asd");
//        sugarApp.testLogin("abubakarkaleem22@gmail.com","123456");
//        UserService service = new UserService();
//        service.getAllUser();


    }

    public void testLogin(String email,String password){
        User user = userService.loginUser(email,password);
        if(user!=null){
            System.out.println(user.toString());
        }else{
            System.out.println("User not found with these credentials");
        }


    }

    public void testFoodService(String foodName){
        foodService.searchByName(foodName);
    }



}
