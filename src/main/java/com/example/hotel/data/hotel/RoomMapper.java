package com.example.hotel.data.hotel;

import com.example.hotel.po.HotelRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Daiqj
 */
@Mapper
@Repository
public interface RoomMapper {

    /**
     * 更新房间信息
     *
     * @param hotelId  酒店id
     * @param roomType 房间类型
     * @param curNum   现有房间数
     * @return int
     * @author ljl
     */
    int updateRoomInfo(@Param("hotelId") Integer hotelId, @Param("roomType") String roomType, @Param("curNum") Integer curNum);

    /**
     * 增加房间
     *
     * @param hotelRoom 酒店房间
     * @return int
     * @author ljl
     */
    int insertRoom(HotelRoom hotelRoom);

    /**
     * 根据酒店id返回房间信息
     *
     * @param hotelId 酒店id
     * @return 酒店房间信息
     * @author ljl
     */
    List<HotelRoom> selectRoomsByHotelId(@Param("hotelId") Integer hotelId);

    /**
     * 获取当前房间数
     *
     * @param hotelId  酒店id
     * @param roomType 房间类型
     * @return int
     * @author ljl
     */
    int getRoomCurNum(@Param("hotelId") Integer hotelId, @Param("roomType") String roomType);

    /**
     * 恢复酒店房间数量
     *
     * @param hotelId  酒店id
     * @param roomType 房间类型
     * @param curNum   现有房间数
     * @author ljl
     */
    void restoreRoom(@Param("hotelId") Integer hotelId, @Param("roomType") String roomType, @Param("curNum") Integer curNum);
}