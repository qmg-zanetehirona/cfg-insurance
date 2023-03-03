package org.example;

public class IncorrectUsernamePasswordException extends Exception{
    public IncorrectUsernamePasswordException(String msm) {
        super(msm);
    }
}
