package org.example.enums;

public enum ManageCreatePolicyOption {

    MANAGE(0), CREATE(1), LOG_OUT(2);

    private final int choice;

    ManageCreatePolicyOption(int choice) {
        this.choice = choice;
    }

    public boolean isManage(){
        return this == MANAGE;
    }

    public boolean isCreate(){
        return this == CREATE;
    }
    public boolean isLogout(){
        return this == LOG_OUT;
    }

    public static ManageCreatePolicyOption from(int option){
        for (ManageCreatePolicyOption manageCreatePolicyOption : values()) {
            if(manageCreatePolicyOption.choice == option) {
                return manageCreatePolicyOption;
            }
        }
        throw new IllegalArgumentException("Invalid manage/create option");
    }
}