package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {

    private final String username;
    private final String password;

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean passwordValidator(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9].*[0-9])(?=.*[A-Z])(?=.*[!@£$%^&*()_+=,./?><:|]).{8,}$");
        return pattern.matcher(password).matches();
    }
}
