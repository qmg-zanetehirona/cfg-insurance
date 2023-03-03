package org.example;

public class IncorrectPasswordRequirementsException extends Exception {
    public IncorrectPasswordRequirementsException(String msm) {
       super(msm);
        System.out.println("Password does not meet the requirements.");
        System.out.print("Please make sure that the password contains:\n" +
                "*  At least 8 characters.\n" +
                "*  At least two digit.\n" +
                "*  At least one upper case alphabet.\n" +
                "*  At least one special character.");
    }
}
