package com.example.hotel.po;

import com.example.hotel.enums.RoomType;

/**
 * @author Daiqj
 */
public class HotelRoom {
    private Integer id;
    private RoomType roomType;
    private Integer hotelId;
    private double price;
    /**
     * 当前剩余可预定房间数
     */
    private int curNum;
    /**
     * 某类型房间总数
     */
    private int total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCurNum() {
        return curNum;
    }

    public void setCurNum(int curNum) {
        this.curNum = curNum;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "HotelRoom{" +
                "id=" + id +
                ", roomType=" + roomType +
                ", hotelId=" + hotelId +
                ", price=" + price +
                ", curNum=" + curNum +
                ", total=" + total +
                '}';
    }
}