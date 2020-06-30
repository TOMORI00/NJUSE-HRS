package com.example.hotel.vo;

/**
 * @author ljl
 */
public class RateVO {

    private Integer hotelId;

    private Double rate;

    private Integer orderId;

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "RateVO{" +
                "hotelId=" + hotelId +
                ", rate=" + rate +
                ", orderId=" + orderId +
                '}';
    }
}
