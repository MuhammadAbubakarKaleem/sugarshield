package com.sugarshield;

import com.sugarshield.user.User;
import com.sugarshield.user.UserService;
import com.sugarshield.foodAnalyser.FoodService;
import com.sugarshield.exerciseTracker.WorkoutPlan;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserService userService = new UserService();
        FoodService foodService = new FoodService();
        WorkoutPlan workoutPlan = new WorkoutPlan();

        User currentUser = null;

        int choice;

        do {

            System.out.println("\n===== SUGAR SHIELD =====");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Search Food");
            System.out.println("4. Generate Workout Plan");
            System.out.println("5. View All Users");
            System.out.println("0. Exit");
            System.out.print("Enter Choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    currentUser = userService.signUp();

                    if (currentUser != null) {
                        System.out.println("Signup Successful");
                    }
                    break;

                case 2:

                    System.out.print("Enter Email: ");
                    String email = sc.next();
                    System.out.print("Enter Password: ");
                    String password = sc.next();
                    currentUser = userService.loginUser(email, password);

                    break;

                case 3:

                    System.out.print("Enter Food Name: ");
                    String foodName = sc.next();

                    foodService.searchByName(foodName);

                    break;

                case 4:

                    System.out.print("Enter Difficulty Level: ");
                    String level = sc.next();

                    workoutPlan.generateWorkoutPlan(level);

                    break;

                case 5:

                    userService.getAllUser();

                    break;

                case 0:

                    System.out.println("Thank You For Using Sugar Shield");

                    break;

                default:

                    System.out.println("Invalid Choice");

            }

        } while (choice != 0);

        sc.close();
    }
}