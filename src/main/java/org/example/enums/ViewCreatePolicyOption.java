package org.example.enums;

public enum ViewCreatePolicyOption {

    VIEW(0), CREATE(1), LOG_OUT(2);

    private final int choice;

    ViewCreatePolicyOption(int choice) {
        this.choice = choice;
    }

    public boolean isView(){
        return this == VIEW;
    }

    public boolean isCreate(){
        return this == CREATE;
    }
    public boolean isLogout(){
        return this == LOG_OUT;
    }

    public static ViewCreatePolicyOption from(int option){
        for (ViewCreatePolicyOption viewCreatePolicyOption : values()) {
            if(viewCreatePolicyOption.choice == option) {
                return viewCreatePolicyOption;
            }
        }
        throw new IllegalArgumentException("Invalid view/create option");
    }
}