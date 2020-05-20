package com.nav.patterns;

import com.nav.patterns.creational.builder.LunchOrder;
import com.nav.patterns.creational.prototype.Movie;
import com.nav.patterns.creational.prototype.Registry;
import com.nav.patterns.creational.singleton.DbSingleton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JavaDesignPatterns {

    public static void main(String[] arg){
        singletonPatternExample1();
        builderPatternExample1();
        protoypePatternExample1();
    }

    private static void singletonPatternExample1() {
        System.out.println("---Singleton Pattern---");
        System.out.println("------Example 1-------");
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

    private static void builderPatternExample1() {
        System.out.println("---Builder Pattern---");
        System.out.println("------Example 1-------");
        LunchOrder.Builder builder = new LunchOrder.Builder();
        builder.bread("Wheat").condiments("Lettuce").dressing("Mayo").meat("Turkey");
        LunchOrder lunchOrder1 = builder.build();
        System.out.println("LunchOrder1" + lunchOrder1);
        builder = new LunchOrder.Builder();
        builder.bread("Wheat").dressing("Mayo").meat("Turkey");
        LunchOrder lunchOrder2 = builder.build();
        System.out.println("LunchOrder2" + lunchOrder2);
    }

    private static void protoypePatternExample1() {
        System.out.println("---Protoype Pattern---");
        System.out.println("------Example 1-------");

        Registry registry = new Registry();
        Movie movie = (Movie) registry.createItem("Movie");
        movie.setTitle("Creational Patters in Java");
        System.out.println("First Movie" + movie.toString());

        Movie anotherMovie = (Movie) registry.createItem("Movie");
        anotherMovie.setTitle("Going of Four");
        System.out.println("Second Movie" + anotherMovie.toString());

    }
}
