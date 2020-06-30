package com.example.hotel.po;

import java.util.Date;

/**
 * @author Daiqj
 */
public class CreditRecord {
    /**
     * 信用记录ID
     */
    private int id;
    /**
     * 用户ID
     */
    private int userId;
    /**
     * 订单ID
     */
    private int orderId;
    /**
     * 信用记录时间
     */
    private Date recordTime;

    private String recordTimeStr = "";

    /**
     * 信用变更值
     */
    private double recordCredit;
    /**
     * 信用变更原因
     */
    private String changeReason;

    /**
     * 信用度结果
     */
    private double creditResult;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public double getRecordCredit() {
        return recordCredit;
    }

    public void setRecordCredit(double recordCredit) {
        this.recordCredit = recordCredit;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public String getRecordTimeStr() {
        return recordTimeStr;
    }

    public void setRecordTimeStr(String recordTimeStr) {
        this.recordTimeStr = recordTimeStr;
    }

    public double getCreditResult() {
        return creditResult;
    }

    public void setCreditResult(double creditResult) {
        this.creditResult = creditResult;
    }

    @Override
    public String toString() {
        return "CreditRecord{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderId=" + orderId +
                ", recordTime=" + recordTime +
                ", recordTimeStr='" + recordTimeStr + '\'' +
                ", recordCredit=" + recordCredit +
                ", changeReason='" + changeReason + '\'' +
                '}';
    }
}