package org.example.question;

import org.example.exceptions.IncorrectPostcodeException;

public interface Question {

    String id();

    String text();

    int options();

    boolean validate(String input) throws Exception;

}
