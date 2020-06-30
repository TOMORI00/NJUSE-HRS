package com.example.hotel.bl.hotel;

import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

/**
 * @author Daiqj
 */
public interface HotelService {

    /**
     * 添加酒店
     *
     * @param hotelVO 酒店VO
     * @return ResponseVO
     */
    ResponseVO addHotel(HotelVO hotelVO);

    /**
     * 预订酒店修改剩余客房信息
     *
     * @param hotelId  酒店ID
     * @param roomType 房间类型
     * @param rooms    房间数
     * @return ResponseVO
     */
    ResponseVO updateRoomInfo(Integer hotelId, String roomType, Integer rooms);

    /**
     * 列表获取所有酒店信息
     *
     * @return 所有酒店信息
     */
    List<HotelVO> retrieveHotels();

    /**
     * 获取某家酒店详细信息
     *
     * @param hotelId 酒店ID
     * @return HotelVO
     */
    HotelVO retrieveHotelDetails(Integer hotelId);

    /**
     * 查看酒店剩余某种房间数量
     *
     * @param hotelId  酒店ID
     * @param roomType 房间类型
     * @return int
     */
    int getRoomCurNum(Integer hotelId, String roomType);

    /**
     * 改变酒店评分,修改订单状态为已完成
     *
     * @param orderId 订单ID
     * @param hotelId 酒店ID
     * @param rate    酒店评分
     * @return ResponseVO
     */
    ResponseVO changeHotelRate(Integer orderId, Integer hotelId, Double rate);

    /**
     * 修改酒店基本信息
     *
     * @param hotelId          酒店ID
     * @param hotelAddress     酒店地址
     * @param bizRegion        商圈
     * @param hotelStar        酒店星级
     * @param hotelDescription 酒店简介
     * @return ResponseVO
     */
    ResponseVO updateHotelInfo(int hotelId, String hotelAddress, String bizRegion, String hotelStar, String hotelDescription);

}