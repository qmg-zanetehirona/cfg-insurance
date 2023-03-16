package org.example.exceptions;

import javax.swing.*;

public class UsernameTakenException extends Exception{
    public UsernameTakenException(String msm) {
        super("Username is taken");
//        JOptionPane.showMessageDialog(null, "Username is taken"
//                , "Error", JOptionPane.ERROR_MESSAGE);;
    }
}
