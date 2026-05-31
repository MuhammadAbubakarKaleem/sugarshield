package com.sugarshield.user;

import java.util.Scanner;

public class UserService {

    private final UserDao userDao;
    private User currentUser;   // 🔥 SESSION USER

    public UserService() {
        userDao = new UserDao();
    }


    public User signUp() {

        User user = getUserInput(); // input only

        User savedUser = userDao.insertUser(user);

        if (savedUser != null) {
            this.currentUser = savedUser; // 🔥 AUTO LOGIN AFTER SIGNUP
            System.out.println("Signup successful ✔");
        }

        return savedUser;
    }


    public User loginUser(String email, String password) {

        User user = userDao.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            this.currentUser = user; // 🔥 STORE SESSION USER
            System.out.println("Login successful ✔");
            return user;
        }

        System.out.println("Invalid credentials ❌");
        return null;
    }


    public User getCurrentUser() {
        return currentUser;
    }


    private User getUserInput() {
        User user = new User();
        Scanner obj = new Scanner(System.in);

        System.out.println("Enter name:");
        user.setFullName(obj.next());

        System.out.println("Enter email:");
        user.setEmail(obj.next());

        System.out.println("Enter password:");
        user.setPassword(obj.next());

        return user;
    }


    public void getAllUser() {
        for (User u : userDao.getAllUsers()) {
            System.out.println(u.printWithoutSensitiveInfo());
        }
    }
}