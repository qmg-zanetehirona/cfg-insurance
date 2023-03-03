package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Repository repository = new Repository();
        LoginService loginService = new LoginService(repository);
        PolicyService policyService = new PolicyService(repository);

        Thread.sleep(3000);

        System.out.println("**********************************");
        System.out.println("*********    WELCOME    **********");
        System.out.println("**********************************");

        Customer customer = loginService.login();
        Customer customerConId = policyService.searchPolicy(customer);

        System.out.println();
        System.out.println("**********************************");
        System.out.println("********   CLOSING APP   *********");
        System.out.println("**********************************");
    }
}