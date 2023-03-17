package org.example.Enum;

public enum LogInSignUpOption {

    LOG_IN(0), SIGN_UP(1);

    private final int i;

    LogInSignUpOption(int i) {
        this.i = i;
    }

    public boolean isLogIn() { return this == LOG_IN;
    }

    public boolean isSignUp(){
        return this == SIGN_UP;
    }

    public static LogInSignUpOption from(int option){
        for (LogInSignUpOption logInSignUpOption : values()) {
            if(logInSignUpOption.i == option) {
                return logInSignUpOption;
            }
        }
        throw new IllegalArgumentException("Invalid login/signup option");
    }

}
