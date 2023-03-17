package org.example.Enum;

public enum ViewCreatePolicyOptions {

    VIEW(0), CREATE(1), LOG_OUT(2);

    private final int i;

    ViewCreatePolicyOptions(int i) {
        this.i = i;
    }

    public boolean isLogIn(){
        return this == VIEW;
    }

    public boolean isSignUp(){
        return this == CREATE;
    }
    public boolean isLogout(){
        return this == LOG_OUT;
    }

    public static ViewCreatePolicyOptions from(int option){
        for (ViewCreatePolicyOptions viewCreatePolicyOptions : values()) {
            if(viewCreatePolicyOptions.i == option) {
                return viewCreatePolicyOptions;
            }
        }
        throw new IllegalArgumentException("Invalid view/create option");
    }
}