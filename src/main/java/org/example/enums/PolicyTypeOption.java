package org.example.enums;

public enum PolicyTypeOption {

    BRONZE("1"), SILVER("2"), GOLD("3");

    private final String type;

    PolicyTypeOption(String type) {
        this.type = type;
    }

    public String getValue() {
        return this.type;
    }
}


