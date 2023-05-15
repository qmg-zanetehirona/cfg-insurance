package org.example;

public class Main {

    public static void main(String[] args) throws Exception {

        Initializer initializer = new Initializer();

        Thread.sleep(3000);

        initializer.getInputDialog().messageForUserMD("Hello, Welcome to ProtectFirst!");

        Customer customer = initializer.getLoginService().login();

        while (true) {
            Customer customerConId = initializer.getPolicyService().searchPolicy(customer);
        }
    }
}
