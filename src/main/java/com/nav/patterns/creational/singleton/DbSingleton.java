package com.nav.patterns.creational.singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbSingleton {

    // This will return only one instance in all the project
    private static volatile DbSingleton instance = null;
    private static volatile Connection conn = null;

    //private constructor
    private DbSingleton() {

        try {
            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // to prevent reinstance the object
        if (conn != null) {
            throw new RuntimeException("Use getConnection() method to create");
        }

        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to create");
        }
    }

    // return the singleton instance
    public static DbSingleton getInstance() {
        //lazy loading
        if (instance == null) {
            synchronized (DbSingleton.class) {
                if (instance == null) {
                    instance = new DbSingleton();
                }
            }
        }

        return instance;
    }

    public Connection getConnection() {
        if (conn == null) {
            synchronized (DbSingleton.class) {
                if (conn == null) {
                    try {
                        String dbUrl = "jdbc:derby:memory:codejava/webdb;create=true";
                        conn = DriverManager.getConnection(dbUrl);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return conn;
    }
}