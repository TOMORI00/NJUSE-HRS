package com.example.hotel.enums;

/**
 * @author Daiqj
 */

public enum BizRegion {
    /**
     * 西单
     */
    XiDan("西单");

    private final String value;

    BizRegion(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
