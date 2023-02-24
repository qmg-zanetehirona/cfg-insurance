package org.example;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {

        Repository repository = new Repository();
        Connection connect = repository.connect();

        Thread.sleep(3000);

        boolean user1;
        boolean isLogIn;

        Scanner scan = new Scanner(System.in);
        System.out.println("***********************");
        System.out.println("*****   WELCOME   *****");
        System.out.println("***********************");

        System.out.print("Would you like to:\n" +
                "1. Log in \n" +
                "2. Sign up\n" +
                "0. Exit\n" +
                "Please enter a number value: -> : ");

        int option = scan.nextInt();

        if (option != 0) {

            int tryAgain = 1;
            while (tryAgain == 1) {
                tryAgain = 2;
                System.out.print("Please enter your username: -> : ");
                String username = scan.next();
                System.out.print("Please enter your password: -> : ");
                String originalPassword = scan.next();
                Customer customer = new Customer(username, originalPassword);
                boolean isvalid = customer.passwordValidator(originalPassword);

                while (!isvalid) {
                    System.out.println("Password does not meet the requeriments.");
                    System.out.print("Please make sure that the password contains:\n" +
                            "*  At least 8 characters.\n" +
                            "*  At least two digit.\n" +
                            "*  At least one upper case alphabet.\n" +
                            "*  At least one special character.\n" +
                            "Please enter your password or 'q' to quit: -> : ");
                    originalPassword = scan.next();
                    if (originalPassword.equals("q")) {
                        System.out.println("\n******   CLOSING APP   ******");
                        System.exit(0);
                    } else {
                        isvalid = customer.passwordValidator(originalPassword);
                    }
                }

                String generatedSecuredPasswordHash = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));

//            if (option != 0) {

                switch (option) {

                    case 1:
                        try (Statement statement = connect.createStatement()) {
                            isLogIn = repository.logInExistingCustomer(statement, username, originalPassword);
                        }
                        if (isLogIn) {
                            System.out.println("*****    ACCESS GRANTED    *****");
                        } else {
                            System.out.println("Password provided does not corresponds to Username");
                            System.out.print("Would you like to try again:\n" +
                                    "1. Yes \n" +
                                    "2. No\n" +
                                    "Please enter a number value: -> : ");
                            tryAgain = scan.nextInt();
                            break;
                        }
                        break;

                    case 2:
                        try (Statement statement = connect.createStatement()) {
                            user1 = repository.createCustomer(statement, username, generatedSecuredPasswordHash);
                        }
                        if (user1) {
                            System.out.println("*****    CUSTOMER CREATED    *****");
                        } else {
                            System.out.println("Username: '" + username + "' is taken");
                            System.out.print("Would you like to try again:\n" +
                                    "1. Yes \n" +
                                    "2. No\n" +
                                    "Please enter a number value: -> : ");
                            tryAgain = scan.nextInt();
                            break;
                        }
                        break;

                    default:
                        System.out.println("WRONG INPUT");
                        break;
                }
            }
        }
    }/// Close Main
}////
