package org.example.question;

public class PolicyTypeQuestion implements Question {

    @Override
    public String id() {
        return "policy_type";
    }

    @Override
    public String text() {
        return "What type of policy do you need?\n" +
                "1. Bronze\n" +
                "2. Silver\n" +
                "3. Gold";
    }

    @Override
    public int options() {
        return 3;
    }

    @Override
    public boolean validate(String answer) {
        return true;
    }
}
