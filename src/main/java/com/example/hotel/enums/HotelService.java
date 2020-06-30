package com.example.hotel.enums;

/**
 * @author Daiqj
 */

public enum HotelService {
    /**
     * WiFi
     */
    WiFi("Wifi"),

    /**
     * HotWater
     */
    HotWater("热水"),

    /**
     * WakeUp
     */
    WakeUp("叫醒服务");

    private final String value;

    HotelService(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
