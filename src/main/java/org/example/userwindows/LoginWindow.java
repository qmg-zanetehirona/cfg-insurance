package org.example.userwindows;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CountDownLatch;

public class LoginWindow extends JFrame {

    private String username;
    private String password;

    public LoginWindow() {

        setTitle("Login Window");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBounds(555, 330, 330, 200);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(70, 8, 70, 20);
        panel.add(usernameLabel);

        JTextField usernameTextField = new JTextField("Maxi",15);
        usernameTextField.setBounds(70, 27, 193, 28);
        panel.add(usernameTextField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(70, 55, 70, 20);
        panel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField("Maxi1234!",15);
        passwordField.setBounds(70, 75, 193, 28);
        panel.add(passwordField);

        JButton okButton = new JButton("Ok");
        okButton.setBounds(120, 110, 90, 25);
        okButton.setEnabled(true);
        panel.add(okButton);

        setContentPane(panel);
        setVisible(true);

        loginButtonActivated(okButton, usernameTextField, passwordField);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void loginButtonActivated(JButton okButton, JTextField usernameTextField, JPasswordField passwordField) {

        CountDownLatch latch = new CountDownLatch(1);
        okButton.addActionListener(e -> {
            username = usernameTextField.getText();
            password = new String(passwordField.getPassword());
            latch.countDown();
            dispose();
        });

        try {
            latch.await();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}



