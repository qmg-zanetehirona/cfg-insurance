package org.example.exceptions;

import javax.swing.*;

public class IncorrectUsernamePasswordException extends Exception {
    public IncorrectUsernamePasswordException(String msm) {
        super("Incorrect Username or password");
    }
}
