package org.example.enums;

public enum BedroomsQuantityOption {

    ONE_BEDROOM("1"), TWO_BEDROOMS("2"), MORE_THAN_TWO_BEDROOMS("3");

    private final String choice;

    BedroomsQuantityOption(String choice) {
        this.choice = choice;
    }

    public String getQuantity() {
        return choice;
    }
}
