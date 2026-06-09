package com.sugarshield.user;

public class UserService {

    private final UserDao userDao;
    private User currentUser;   // session user

    public UserService() {
        userDao = new UserDao();
    }


    public User signUp(User user) {
        if (user == null ||
                user.getFullName() == null || user.getFullName().trim().isEmpty() ||
                user.getEmail() == null || user.getEmail().trim().isEmpty() ||
                user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            System.out.println("Invalid input. Signup failed.");
            return null;
        }

        user.setFullName(user.getFullName().trim());
        user.setEmail(user.getEmail().trim());

        User existingUser = userDao.getUserByEmail(user.getEmail());
        if (existingUser != null) {
            System.out.println("User already signed up with this email.");
            return null;
        }

        User savedUser = userDao.insertUser(user);
        if (savedUser != null) {
            this.currentUser = savedUser;
            System.out.println("Signup successful");
        }

        return savedUser;
    }


    public User loginUser(String email, String password) {

        User user = userDao.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            this.currentUser = user; // store session user
            System.out.println("Login successful");
            return user;
        }

        System.out.println("Invalid credentials");
        return null;
    }


    public User getCurrentUser() {
        return currentUser;
    }

    public User getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    public User updateUser(User user) {
        if (user == null ||
                user.getId() <= 0 ||
                user.getFullName() == null || user.getFullName().trim().isEmpty() ||
                user.getEmail() == null || user.getEmail().trim().isEmpty() ||
                user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            System.out.println("Invalid input. Profile update failed.");
            return null;
        }

        if (user.getAge() < 0 || user.getHeight() < 0 || user.getWeight() < 0) {
            System.out.println("Invalid health details. Profile update failed.");
            return null;
        }

        user.setFullName(user.getFullName().trim());
        user.setEmail(user.getEmail().trim());

        User existingUser = userDao.getUserByEmail(user.getEmail());
        if (existingUser != null && existingUser.getId() != user.getId()) {
            System.out.println("Email already belongs to another user.");
            return null;
        }

        boolean updated = userDao.updateUser(user);
        if (updated) {
            this.currentUser = user;
            return user;
        }

        return null;
    }


    public void getAllUser() {
        for (User u : userDao.getAllUsers()) {
            System.out.println(u.printWithoutSensitiveInfo());
        }
    }
}
