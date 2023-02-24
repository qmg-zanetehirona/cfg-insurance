package org.example;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class Repository {
    private Connection connect;
    private final String url = "jdbc:postgresql://localhost:5432/users";
    private final String user = "postgres";
    private final String password = "";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            conn.isValid(1000);

            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public boolean createCustomer(Statement statement, String username, String password) throws SQLException {
        ResultSet resultSet = statement.executeQuery(String.format(" SELECT * FROM usersregistration WHERE user_username ='%s'", username));
        if (resultSet.next()) {
            return false;
        } else {
            statement.executeUpdate(
                    String.format(" INSERT INTO usersregistration (user_username,user_password) VALUES ('%s','%s')", username, password));
            return true;
        }
    }

    public boolean logInExistingCustomer(Statement statement, String username, String originalPassword) throws SQLException {
        ResultSet resultSet = statement.executeQuery(String.format(" SELECT * FROM usersregistration WHERE user_username ='%s'", username));
        if (!resultSet.next()) {
            return false;
        } else {
            String userpassword = resultSet.getString("user_password");
            return BCrypt.checkpw(originalPassword, userpassword);
        }
    }
}