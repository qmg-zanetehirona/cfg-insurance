package org.example.Enum;

public enum YesNoOption {
    YES(0), NO(1);

    private final int i;

    YesNoOption(int i) {
        this.i = i;
    }

    public boolean isYes(){
        return this == YES;
    }

    public boolean isNo(){
        return this == NO;
    }

    public static YesNoOption from(int option){
        for (YesNoOption yesNoOption : values()) {
            if(yesNoOption.i == option) {
                return yesNoOption;
            }
        }
        throw new IllegalArgumentException("Invalid yes/no option");
    }
}
