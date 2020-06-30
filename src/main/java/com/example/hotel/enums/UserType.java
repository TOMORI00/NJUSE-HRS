package com.example.hotel.enums;

/**
 * @author chenyizong
 */
public enum UserType {
    /**
     * 客户
     */
    Client("1"),

    /**
     * 酒店工作人员
     */
    Staff("2"),

    /**
     * 网站营销人员
     */
    Busi("3"),

    /**
     * 网站管理人员
     */
    Admin("4");
    private String value;

    UserType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}