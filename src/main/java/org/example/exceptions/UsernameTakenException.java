package org.example.exceptions;

import javax.swing.*;

public class UsernameTakenException extends Exception{
    public UsernameTakenException(String msm) {
        super("Username is taken");
    }
}
