package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);

        LoginService loginService = new LoginService();

        Thread.sleep(3000);

        System.out.println("**********************************");
        System.out.println("*********    WELCOME    **********");
        System.out.println("**********************************");

        String tryAgain = "1";
        while (tryAgain.equals("1")) {
            tryAgain = "2";
            try {
                loginService.userLoginOrRegistration();
            } catch (UsernameTakenException | IncorrectUsernamePasswordException e) {
                System.out.println(e.getMessage());
                System.out.print("Would you like to try again:\n" +
                        "1. Yes \n" +
                        "Any other key to EXIT: \n" +
                        "Please enter a value: -> : ");
                tryAgain = scan.next();
            }
        }
        System.out.println();
        System.out.println("**********************************");
        System.out.println("********   CLOSING APP   *********");
        System.out.println("**********************************");
    }
}