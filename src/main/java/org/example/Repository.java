package org.example;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Repository {
    Scanner scan = new Scanner(System.in);
    private Connection connect;

    public Repository() {
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
    }

    public Customer createCustomer(String username, String password) throws SQLException, UsernameTakenException {
        ResultSet resultSet = connect.createStatement()
                .executeQuery(String.format(" SELECT * FROM usersregistration WHERE user_username ='%s'", username));
        if (resultSet.next()) {
            throw new UsernameTakenException("Username is taken");
        } else {
            String passwordValid = isPasswordValid(password);
            connect.createStatement().executeUpdate(
                    String.format(" INSERT INTO usersregistration (user_username,user_password) VALUES ('%s','%s')", username, passwordValid));
            return new Customer(username);
        }
    }

    public Customer logInExistingCustomer(String username, String originalPassword) throws SQLException, IncorrectUsernamePasswordException {
        ResultSet resultSet = connect.createStatement()
                .executeQuery(String.format(" SELECT * FROM usersregistration WHERE user_username ='%s'", username));
        if (resultSet.next()) {
            String userpassword = resultSet.getString("user_password");
            if (BCrypt.checkpw(originalPassword, userpassword)) {
                return new Customer(username);
            }
        }
        throw new IncorrectUsernamePasswordException("Incorrect username or password");
    }

    private String isPasswordValid(String originalPassword) {
        boolean isValid = passwordValidator(originalPassword);
        while (!isValid) {
            System.out.println("Password does not meet the requirements.");
            System.out.print("Please make sure that the password contains:\n" +
                    "*  At least 8 characters.\n" +
                    "*  At least two digit.\n" +
                    "*  At least one upper case alphabet.\n" +
                    "*  At least one special character.\n" +
                    "Please enter your password or 'q' to quit: -> : ");
            originalPassword = scan.next();
            if (originalPassword.equals("q")) {
                System.out.println("**********************************");
                System.out.println("********   CLOSING APP   *********");
                System.out.println("**********************************");
                System.exit(0);
            } else {
                isValid = passwordValidator(originalPassword);
            }
        }
        return BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
    }

    private boolean passwordValidator(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9].*[0-9])(?=.*[A-Z])(?=.*[!@£$%^&*()_+=,./?><:|]).{8,}$");
        return pattern.matcher(password).matches();
    }
}







