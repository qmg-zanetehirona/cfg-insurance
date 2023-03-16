package org.example.repositories;

import org.example.Customer;
import org.example.exceptions.IncorrectPasswordRequirementsException;
import org.example.exceptions.IncorrectUsernamePasswordException;
import org.example.exceptions.UsernameTakenException;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class RepositoryLogIn {

    public Connection connect;

    public RepositoryLogIn(Connection connect) {
        this.connect = connect;
    }

    public Customer createCustomer(String username, String password) throws SQLException, UsernameTakenException, IncorrectPasswordRequirementsException, IncorrectUsernamePasswordException {
        ResultSet resultSet = connect.createStatement()
                .executeQuery(String.format(" SELECT * FROM usersregistration WHERE user_username ='%s'", username));
        if (resultSet.next()) {
            throw new UsernameTakenException("Username is taken");
        }
        passwordValid(password);
        String crypt = cryptPassword(password);
        connect.createStatement().executeUpdate(
                String.format(" INSERT INTO usersregistration (user_username,user_password) VALUES ('%s','%s')", username, crypt));

        return logInExistingCustomer(username, password);
    }

    public Customer logInExistingCustomer(String username, String originalPassword) throws SQLException, IncorrectUsernamePasswordException {
        ResultSet resultSet = connect.createStatement()
                .executeQuery(String.format(" SELECT * FROM usersregistration WHERE user_username ='%s'", username));
        if (resultSet.next()) {
            String userpassword = resultSet.getString("user_password");
            int userId = resultSet.getInt("UserId");
            if (BCrypt.checkpw(originalPassword, userpassword)) {
                return new Customer(userId, username);
            }
        }
        throw new IncorrectUsernamePasswordException("Incorrect username or password");
    }

    public void passwordValid(String originalPassword) throws IncorrectPasswordRequirementsException {
        boolean isValid = passwordValidator(originalPassword);
        if (!isValid) {
            throw new IncorrectPasswordRequirementsException("Password does not meet the requirements");
        }
    }

    public String cryptPassword(String originalPassword) {
        return BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
    }

    public boolean passwordValidator(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9].*[0-9])(?=.*[A-Z])(?=.*[!@£$%^&*()_+=,./?><:|]).{8,}$");
        return pattern.matcher(password).matches();
    }

}









