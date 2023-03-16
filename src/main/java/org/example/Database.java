package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public Connection connect;

    public Connection connectWithDB() {
        try {
            String password = "";
            String user = "postgres";
            String url = "jdbc:postgresql://localhost:5432/users";
            connect = DriverManager.getConnection(url, user, password);
            connect.isValid(1000);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connect;
    }
}
