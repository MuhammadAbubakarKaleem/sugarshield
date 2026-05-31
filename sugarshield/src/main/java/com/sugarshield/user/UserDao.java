package com.sugarshield.user;


import com.sugarshield.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import java.sql.Statement;
import java.util.ArrayList;


public class UserDao {

    public User getUserByEmail(String email){
        User user = null;
        String sql  = "SELECT * FROM Users WHERE email = ?";

        try (Connection com = DBConnection.getConnection()){
            PreparedStatement ps  = com.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {   // only one row expected
                user = new User();
                user.setId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAge(rs.getInt("age"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         return user;
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> usersArray = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (Connection com = DBConnection.getConnection()){
            PreparedStatement ps  = com.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setFullName(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setAge(rs.getInt("age"));
                usersArray.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersArray;
    }
    // function to sing up

    public User insertUser(User user) {

        // validation
        if (user == null || user.getFullName() == null ||
            user.getEmail() == null ||
                user.getPassword() == null) {

            System.out.println("Invalid input. Signup failed.");
            return null;
        }

        String sql = "INSERT INTO Users(full_name, email, password) VALUES (?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getFullName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());

            int rows = statement.executeUpdate();

            if (rows > 0) {

                try (ResultSet rs = statement.getGeneratedKeys()) {

                    if (rs.next()) {
                        user.setId(rs.getInt(1)); // 🔥 DB generated ID
                    }
                }

                System.out.println("Signup successful ✔ User saved in database.");
                return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }



}
