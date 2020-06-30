package com.example.hotel.bl.hotel;

import com.example.hotel.po.HotelRoom;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

/**
 * @author Daiqj
 */
public interface RoomService {

    /**
     * 获取某个酒店的全部房间信息
     *
     * @param hotelId 酒店ID
     * @return 某个酒店的全部房间信息
     */
    List<HotelRoom> retrieveHotelRoomInfo(Integer hotelId);

    /**
     * 添加酒店客房信息
     *
     * @param hotelRoom 酒店房间VO
     * @return ResponseVO
     */
    ResponseVO insertRoomInfo(HotelRoom hotelRoom);

    /**
     * 预订酒店后更新客房房间数量
     *
     * @param hotelId  酒店ID
     * @param roomType 房间类型
     * @param rooms    房间数
     * @return ResponseVO
     */
    ResponseVO updateRoomInfo(Integer hotelId, String roomType, Integer rooms);

    /**
     * 获取酒店指定房间剩余数量
     *
     * @param hotelId  酒店ID
     * @param roomType 房间类型
     * @return int
     */
    int getRoomCurNum(Integer hotelId, String roomType);

    /**
     * 恢复某个酒店的某个房型的数量
     *
     * @param hotelId  酒店ID
     * @param roomType 房间类型
     * @param rooms    房间数量
     * @return ResponseVO
     */
    ResponseVO restoreRoom(Integer hotelId, String roomType, Integer rooms);
}