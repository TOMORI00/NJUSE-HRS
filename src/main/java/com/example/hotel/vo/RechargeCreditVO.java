package com.example.hotel.vo;

/**
 * @author ljl
 */
public class RechargeCreditVO {
    Integer userId;
    Integer changeCredit;

    public Integer getChangeCredit() {
        return changeCredit;
    }

    public void setChangeCredit(Integer changeCredit) {
        this.changeCredit = changeCredit;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "RechargeCreditVO{" +
                "userId=" + userId +
                ", changeCredit=" + changeCredit +
                '}';
    }
}
