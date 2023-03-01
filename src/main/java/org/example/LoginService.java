package org.example;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginService {

    Scanner scan = new Scanner(System.in);
    Customer customer;
    Repository repository = new Repository();

    private String UserChooseFunction() {

        System.out.print("Would you like to:\n" +
                "1. Log in \n" +
                "2. Sign up\n" +
                "Any other key to EXIT: \n" +
                "Please enter a value: -> : ");
        return scan.next();
    }
    private Customer runFunctionSelected(String option, String username, String originalPassword)
            throws SQLException, IncorrectUsernamePasswordException, UsernameTakenException {

        if (option.equals("1")) {
            customer = repository.logInExistingCustomer(username, originalPassword);
            System.out.println("**********************************");
            System.out.println("******    ACCESS GRANTED    ******");
            System.out.println("**********************************");
        }else{
                customer = repository.createCustomer(username, originalPassword);
                System.out.println("**********************************");
                System.out.println("*****    CUSTOMER CREATED    *****");
                System.out.println("**********************************");
        }
        return customer;
    }

    public void userLoginOrRegistration() throws Exception {

        String option = UserChooseFunction();

        if(option.equals("1")||option.equals("2")) {
            System.out.print("Please enter your username: -> : ");
            String username = scan.next();
            System.out.print("Please enter your password: -> : ");
            String originalPassword = scan.next();
            customer = runFunctionSelected(option, username, originalPassword);
        }
        System.out.println("**********************************");
        System.out.println("********   CLOSING APP   *********");
        System.out.println("**********************************");
        System.exit(0);
    }
}
