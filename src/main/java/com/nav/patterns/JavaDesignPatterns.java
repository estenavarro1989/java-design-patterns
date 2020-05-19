package com.nav.patterns;

import com.nav.patterns.creational.singleton.DbSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaDesignPatterns {

    public static void main(String[] arg){
        singletonPattern();
    }

    private static void singletonPattern() {
        System.out.println("-----------------------");
        System.out.println("---Singleton Pattern---");
        DbSingleton instance1 = DbSingleton.getInstance();
        System.out.println("Instance 1: " + instance1);
        Connection conn = instance1.getConnection();
        Statement statement;
        try {
            statement = conn.createStatement();
            int count = statement
                    .executeUpdate("CREATE TABLE Address (ID INT, StreetName VARCHAR(20),"
                            + " City VARCHAR(20))");
            System.out.println("Table created with instance 1");
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        DbSingleton instance2 = DbSingleton.getInstance();
        System.out.println("Instance 2: " + instance2);
        conn = instance2.getConnection();
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("Select * from Address");

            System.out.println("Table queried with instance 2");
            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
