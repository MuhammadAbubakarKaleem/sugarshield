package com.sugarshield.user;


import com.sugarshield.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


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

}
