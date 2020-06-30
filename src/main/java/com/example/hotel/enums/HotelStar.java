package com.example.hotel.enums;

/**
 * @author Daiqj
 */

public enum HotelStar {
    /**
     * Five
     */
    Five("五星级"),

    /**
     * Four
     */
    Four("四星级"),

    /**
     * Three
     */
    Three("三星级");

    private final String value;

    HotelStar(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
