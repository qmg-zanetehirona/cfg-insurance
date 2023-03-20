package org.example.exceptions;

import javax.swing.*;

public class IncorrectPasswordRequirementsException extends Exception {
    public IncorrectPasswordRequirementsException(String msm) {
        super("Password does not meet the requirements\n" +
                "Please make sure that the password contains:\n" +
                "*  At least 8 characters.\n" +
                "*  At least two digit.\n" +
                "*  At least one upper case alphabet.\n" +
                "*  At least one special character.");
    }
}
