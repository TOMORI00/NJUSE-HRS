package com.example.hotel.vo;

/**
 * @author ljl
 */
public class TimeCouponVO extends CouponVO {
    private Integer hotelId;

    public Integer getHotelId() {
        return hotelId;
    }


    private String startTime;
    private String endTime;
    private Double discount;
    private Integer targetMoney;
    private Integer discountMoney;

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Integer getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(Integer targetMoney) {
        this.targetMoney = targetMoney;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }

    @Override
    public String toString() {
        return "TimeCouponVO{" +
                "hotelId=" + hotelId +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", discount=" + discount +
                ", targetMoney=" + targetMoney +
                ", discountMoney=" + discountMoney +
                '}';
    }
}
