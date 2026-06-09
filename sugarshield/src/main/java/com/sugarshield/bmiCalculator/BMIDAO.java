package com.sugarshield.bmiCalculator;



import com.sugarshield.db.DBConnection;
import java.sql.*;



public class BMIDAO {

    public BMI getBMIByUserId(int userId) {

        BMI bmiRecord = null;

        String sql = "  SELECT height, weight FROM Users WHERE user_id = ? ";

        try (
                Connection conn = DBConnection.getConnection();
                PreparedStatement pst = conn.prepareStatement(sql)
        ) {

            pst.setInt(1, userId);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                String heightText = rs.getString("height");
                float height = Float.parseFloat(heightText);
                float weight = rs.getFloat("weight");

                float bmiValue = BMICalculator.calculateBMIFromFeetInches(weight, heightText);
                String status = BMICalculator.getBMIStatus(bmiValue);

                bmiRecord = new BMI();
                bmiRecord.setUserId(userId);
                bmiRecord.setHeight(height);
                bmiRecord.setWeight(weight);
                bmiRecord.setBmi(bmiValue);
                bmiRecord.setBmiStatus(status);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return bmiRecord;
    }
}
