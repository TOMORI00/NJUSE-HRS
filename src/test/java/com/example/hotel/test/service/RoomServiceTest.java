package com.example.hotel.test.service;

import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.enums.RoomType;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.test.BasicTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RoomServiceTest extends BasicTest {
    @Autowired
    RoomService roomService;

    @Test
    public void retrieveHotelRoomInfo() {
        //正常
        int hotelId_Normal = 1;
        roomService.retrieveHotelRoomInfo(hotelId_Normal).forEach(hotelRoom -> System.out.println(hotelRoom.toString()));

        //异常
        int hotelId_Error = -1;
        roomService.retrieveHotelRoomInfo(hotelId_Error).forEach(hotelRoom -> System.out.println(hotelRoom.toString()));

    }

    @Test
    @Transactional
    @Rollback
    public void insertRoomInfo() {

        //正常
        HotelRoom hotelRoom_Normal = new HotelRoom();
        hotelRoom_Normal.setId(1);
        hotelRoom_Normal.setRoomType(RoomType.BigBed);
        hotelRoom_Normal.setHotelId(2);
        hotelRoom_Normal.setPrice(1000.0);
        hotelRoom_Normal.setCurNum(1);
        hotelRoom_Normal.setTotal(10);
        assertTrue(roomService.insertRoomInfo(hotelRoom_Normal).getSuccess());

        //异常

        HotelRoom hotelRoom_Error = new HotelRoom();
        hotelRoom_Error.setId(-1);
        hotelRoom_Error.setRoomType(RoomType.BigBed);
        hotelRoom_Error.setHotelId(-1);
        hotelRoom_Error.setPrice(-1000.0);
        hotelRoom_Error.setCurNum(-1);
        hotelRoom_Error.setTotal(-10);
        assertFalse(roomService.insertRoomInfo(hotelRoom_Error).getSuccess());


    }

    @Test
    @Transactional
    @Rollback
    public void updateRoomInfo() {// 原void
        //正常
        Integer hotelId_Normal = 1;
        String roomType_Normal = "大床房";
        Integer rooms_Normal = 1;
        assertTrue(roomService.updateRoomInfo(hotelId_Normal, roomType_Normal, rooms_Normal).getSuccess());

        //异常
        Integer hotelId_Error = -1;
        String roomType_Error = "test_Error";
        Integer rooms_Error = -1;
        assertFalse(roomService.updateRoomInfo(hotelId_Error, roomType_Error, rooms_Error).getSuccess());


    }

    @Test
    public void getRoomCurNum() {
        //正常
        Integer hotelId_Normal = 1;
        String roomType_Normal = "BigBed";
        System.out.println(roomService.getRoomCurNum(hotelId_Normal, roomType_Normal));

        //异常
        Integer hotelId_Error = -1;
        String roomType_Error = "test_room";
        System.out.println(roomService.getRoomCurNum(hotelId_Error, roomType_Error));


    }

    @Test
    @Transactional
    @Rollback
    public void restoreRoom() {
        //正常
        Integer hotelId_Normal = 1;
        String roomType_Normal = "BigBed";
        Integer rooms_Normal = 1;
        roomService.restoreRoom(hotelId_Normal, roomType_Normal, rooms_Normal);

        //异常
        Integer hotelId_Error = -1;
        String roomType_Error = "大床房";
        Integer rooms_Error = -1;
        roomService.restoreRoom(hotelId_Error, roomType_Error, rooms_Error);

    }

}