package org.example.enums;

public enum YesNoOption {
    YES(0), NO(1);

    private final int choice;

    YesNoOption(int choice) {
        this.choice = choice;
    }

    public boolean isYes(){
        return this == YES;
    }

    public boolean isNo(){
        return this == NO;
    }

    public static YesNoOption from(int option){
        for (YesNoOption yesNoOption : values()) {
            if(yesNoOption.choice == option) {
                return yesNoOption;
            }
        }
        throw new IllegalArgumentException("Invalid yes/no option");
    }
}
