package com.example.hotel.test.Unit.data;

import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.enums.RoomType;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.test.BasicTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

public class RoomMapperTest extends BasicTest {
    @Autowired
    RoomMapper roomMapper;

    @Test
    @Transactional
    @Rollback
    public void updateRoomInfoTest1() {
        roomMapper.updateRoomInfo(1, "BigBed", 3);
    }

    @Test
    @Transactional
    @Rollback
    public void insertRoomTest1() {
        HotelRoom hotelRoom = new HotelRoom();
        hotelRoom.setCurNum(20);
        hotelRoom.setHotelId(3);
        hotelRoom.setPrice(100);
        hotelRoom.setRoomType(RoomType.Family);
        hotelRoom.setTotal(20);
        try {
            roomMapper.insertRoom(hotelRoom);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Transactional
    @Rollback
    //同一酒店插入相同客房
    public void insertRoomTest2() {
        HotelRoom hotelRoom1 = new HotelRoom();
        hotelRoom1.setCurNum(20);
        hotelRoom1.setHotelId(3);
        hotelRoom1.setPrice(100);
        hotelRoom1.setRoomType(RoomType.Family);
        hotelRoom1.setTotal(20);
        roomMapper.insertRoom(hotelRoom1);

        HotelRoom hotelRoom2 = new HotelRoom();
        hotelRoom2.setCurNum(20);
        hotelRoom2.setHotelId(3);
        hotelRoom2.setPrice(100);
        hotelRoom2.setRoomType(RoomType.Family);
        hotelRoom2.setTotal(20);
        try {
            roomMapper.insertRoom(hotelRoom2);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void selectRoomsByHotelIdTest1() {
        List<HotelRoom> hotelRoomList = roomMapper.selectRoomsByHotelId(1);
        assertNotNull(hotelRoomList);
    }

    @Test
    public void getRoomCurNumTest1() {
        int num = roomMapper.getRoomCurNum(1, "BigBed");
    }

    @Test
    @Transactional
    @Rollback
    public void restoreRoomTest1() {
        int restoreNum = 3;
        int num = roomMapper.getRoomCurNum(1, "BigBed");
        roomMapper.restoreRoom(1, "BigBed", restoreNum);
        int afterNum = roomMapper.getRoomCurNum(1, "BigBed");
        assertEquals(restoreNum + num, afterNum);
    }


}
