package org.example;

import org.example.services.PolicyService;

public class Main {

    public static void main(String[] args) throws Exception {

        Inicializer inicializer = new Inicializer();
        PolicyService policyService = new PolicyService(inicializer.inputDialog, inicializer.outputDialog, inicializer.repositoryPolicy, inicializer.policyCreator);

        Thread.sleep(3000);

        inicializer.inputDialog.messageForUserMD("Hello, Welcome to ProtectFirst!");

        Customer customer = inicializer.loginService.login();

        while (true) {
            Customer customerConId = policyService.searchPolicy(customer);
        }
    }
}
