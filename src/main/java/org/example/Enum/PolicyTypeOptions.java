package org.example.Enum;

public enum PolicyTypeOptions {

    BRONZE("1"), SILVER("2"), GOLD("3");

    private final String i;

    PolicyTypeOptions(String i) {
        this.i = i;
    }

    public String getValue() {
        return this.i;
    }
}

