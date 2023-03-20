package org.example.question;

public class BedroomQuantityQuestion implements Question {

    @Override
    public String id() {
        return "no_bedrooms";
    }

    @Override
    public String text() {
        return "How many bedrooms does your house have?\n" +
                "1. One Bedroom\n" +
                "2. Two Bedrooms\n" +
                "3. More than three Bedrooms";
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
