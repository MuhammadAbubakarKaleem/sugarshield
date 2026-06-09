package com.sugarshield.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:33061/sugarshield";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection getConnection () throws Exception{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}