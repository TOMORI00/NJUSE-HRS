package com.example.hotel.vo;

/**
 * @author ljl
 */
public class CancelAbnormalOrderVO {
    Integer orderId;
    /**
     * 百分比整数
     */
    Integer ratio;

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }

    public Integer getRatio() {
        return ratio;
    }

    @Override
    public String toString() {
        return "CancelAbnormalOrderVO{" +
                "orderId=" + orderId +
                ", ratio=" + ratio +
                '}';
    }
}
