package org.example.question;

import org.example.exceptions.IncorrectPostcodeException;

import java.util.regex.Pattern;

public class PostcodeQuestion implements Question {
    @Override
    public String id() {
        return "postcode";
    }

    @Override
    public String text() {
        return "Please Enter your postcode";
    }

    @Override
    public int options() {
        return 0;
    }

    @Override
    public boolean validate(String postcode) throws IncorrectPostcodeException {
        if (!postCodeValidator(postcode)) {
            throw new IncorrectPostcodeException("Incorrect postcode");
        }
        return true;
    }

    private boolean postCodeValidator(String postcode) {
        Pattern pattern = Pattern.compile("^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$");
        return pattern.matcher(postcode).matches();
    }
}
