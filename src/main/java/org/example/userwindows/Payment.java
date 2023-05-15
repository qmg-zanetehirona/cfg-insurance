package org.example.userwindows;

import com.toedter.calendar.JMonthChooser;

import javax.swing.*;

public class Payment extends JFrame {
    public Payment() {

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel label1 = new JLabel("Card Number:");
        label1.setBounds(30, 50, 150, 30);
        panel.add(label1);

        JTextField textField1 = new JTextField("4716-1089-9971-6531");
        textField1.setBounds(140, 50, 210, 30);
        panel.add(textField1);

        JLabel label2 = new JLabel("Expiration Date:");
        label2.setBounds(30, 100, 150, 30);
        panel.add(label2);

        JMonthChooser monthBox = new JMonthChooser();
        monthBox.setBounds(140, 100, 120, 30);
        panel.add(monthBox);

        JComboBox<String> yearBox = new JComboBox<>(new String[]{"2023", "2024", "2025", "2026", "2027"});
        yearBox.setBounds(260, 100, 90, 30);
        panel.add(yearBox);

        JLabel label3 = new JLabel("CVV:");
        label3.setBounds(30, 150, 150, 30);
        panel.add(label3);

        JPasswordField passwordField = new JPasswordField("257");
        passwordField.setBounds(140, 150, 40, 30);
        panel.add(passwordField);

        ImageIcon icon = new ImageIcon("/Users/zanete.molina/Desktop/cfg-insurance/src/main/resources/VisaMClogo.png");
        JLabel label = new JLabel(icon);
        label.setBounds(40, 200, 300, 47);
        panel.add(label);

        setContentPane(panel);

    }
}

