package org.example;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Repository {

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

    public Customer createCustomer(String username, String password) throws SQLException, UsernameTakenException, IncorrectPasswordRequirementsException {
        ResultSet resultSet = connect.createStatement()
                .executeQuery(String.format(" SELECT * FROM usersregistration WHERE user_username ='%s'", username));
        if (resultSet.next()) {
            throw new UsernameTakenException("Username is taken");
        }
        String passwordValid = passwordValid(password);
        String crypt = cryptPassword(passwordValid);
        connect.createStatement().executeUpdate(
                String.format(" INSERT INTO usersregistration (user_username,user_password) VALUES ('%s','%s')", username, crypt));
        return new Customer(username);
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

    public String passwordValid(String originalPassword) throws IncorrectPasswordRequirementsException {
        boolean isValid = passwordValidator(originalPassword);
        if (!isValid) {
            throw new IncorrectPasswordRequirementsException("Password does not meet the requirements");
        }
        return originalPassword;
    }

    public String cryptPassword(String originalPassword) {
        return BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
    }

    public boolean passwordValidator(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9].*[0-9])(?=.*[A-Z])(?=.*[!@£$%^&*()_+=,./?><:|]).{8,}$");
        return pattern.matcher(password).matches();
    }

    /////////////////////////////////////////////////////  POLICY SERVICE

    public int getCustomerId(String username) throws SQLException, IncorrectUsernamePasswordException {
        ResultSet resultSet = connect.createStatement().
                executeQuery(String.format(" SELECT UserId FROM usersregistration WHERE user_username = '%s'", username));
        if (resultSet.next()) {
            return resultSet.getInt("UserId");
        }
        throw new IncorrectUsernamePasswordException("Incorrect username or password");
    }
    public List<String> getListPolicyTypes(int customerId) throws SQLException {
        List<String> ListPolicytypes = new ArrayList<>();
        ResultSet resultSet = connect.createStatement().executeQuery(String.format(" SELECT P.PolicyType\n" +
                "FROM UsersRegistration\n" +
                "INNER JOIN Policies P on UsersRegistration.UserId = P.User_Id\n" +
                "WHERE UsersRegistration.UserId='%s'", customerId));
        while(resultSet.next()) {
            String gePolicyTypes = resultSet.getString("PolicyType");
            ListPolicytypes.add(gePolicyTypes);
        }
        return ListPolicytypes;
    }

}









