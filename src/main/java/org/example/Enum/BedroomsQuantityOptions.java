package org.example.Enum;

public enum BedroomsQuantityOptions {

    ONE_BEDROOM("1"), TWO_BEDROOMS("2"), MORE_THAN_TWO_BEDROOMS("3");

    private final String i;

    BedroomsQuantityOptions(String i) {
        this.i = i;
    }

    public String getQuantity() {
        return i;
    }
}
