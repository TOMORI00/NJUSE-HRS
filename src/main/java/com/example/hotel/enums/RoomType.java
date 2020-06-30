package com.example.hotel.enums;

/**
 * @author Daiqj
 */

public enum RoomType {
    /**
     * BigBed
     */
    BigBed("大床房"),

    /**
     * DoubleBed
     */
    DoubleBed("双床房"),

    /**
     * Family
     */
    Family("家庭房");
    private final String value;

    RoomType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
