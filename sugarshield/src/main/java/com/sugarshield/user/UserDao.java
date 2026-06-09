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
                user = createUserFromResultSet(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
         return user;
    }

    public User getUserById(int userId){
        User user = null;
        String sql  = "SELECT * FROM Users WHERE user_id = ?";

        try (Connection com = DBConnection.getConnection()){
            PreparedStatement ps  = com.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = createUserFromResultSet(rs);
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
                User user = createUserFromResultSet(rs);
                usersArray.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usersArray;
    }
    // function to sing up

    public User insertUser(User user) {



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

    public boolean updateUser(User user) {

        String sql = "UPDATE Users SET full_name=?, email=?, password=?, age=?, height=?, weight=?, gender=?, diabetes_type=? WHERE user_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getFullName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getAge());
            statement.setDouble(5, user.getHeight());
            statement.setDouble(6, user.getWeight());
            if (user.getGender() == 0) {
                statement.setString(7, "");
            } else {
                statement.setString(7, String.valueOf(user.getGender()));
            }
            statement.setString(8, user.getDiabetesType());
            statement.setInt(9, user.getId());

            int rows = statement.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private User createUserFromResultSet(ResultSet rs) throws Exception {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setFullName(rs.getString("full_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setAge(rs.getInt("age"));
        user.setHeight(rs.getDouble("height"));
        user.setWeight(rs.getDouble("weight"));

        String gender = rs.getString("gender");
        if (gender != null && !gender.trim().isEmpty()) {
            user.setGender(gender.charAt(0));
        }

        user.setDiabetesType(rs.getString("diabetes_type"));
        return user;
    }


}
