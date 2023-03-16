package org.example.exceptions;

import javax.swing.*;

public class IncorrectDateSelected extends Exception{

    public IncorrectDateSelected(String msm) {
        super("Incorrect Date Selected");
//        JOptionPane.showMessageDialog(null, "Incorrect Date Selected",
//                "Error", JOptionPane.ERROR_MESSAGE);
    }
}
