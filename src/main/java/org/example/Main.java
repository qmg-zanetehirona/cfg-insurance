package org.example;

import org.example.services.PolicyService;

public class Main {

    public static void main(String[] args) throws Exception {

        Initializer initializer = new Initializer();

        Thread.sleep(3000);

        initializer.inputDialog.messageForUserMD("Hello, Welcome to ProtectFirst!");

        Customer customer = initializer.loginService.login();

        while (true) {
            Customer customerConId = initializer.getPolicyService().searchPolicy(customer);
        }
    }
}
