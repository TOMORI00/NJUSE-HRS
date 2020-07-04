package com.example.hotel.test.Integration.service;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.test.BasicTest;
import com.example.hotel.vo.HotelVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

public class HotelServiceTest extends BasicTest {

    @Autowired
    HotelService hotelService;

    @Test
    @Transactional
    @Rollback
    public void addHotel() {
        HotelVO hotelVO_Normal = new HotelVO();
        hotelVO_Normal.setId(100);
        hotelVO_Normal.setName("test_name");
        hotelVO_Normal.setAddress("test_address");
        hotelVO_Normal.setBizRegion("XiDan");
        hotelVO_Normal.setHotelStar("Five");
        hotelVO_Normal.setRate(5.0);
        hotelVO_Normal.setDescription("test_description");
        hotelVO_Normal.setPhoneNum("test_phoneNum");
        hotelVO_Normal.setManagerId(6);
        assertTrue(hotelService.addHotel(hotelVO_Normal).getSuccess());

        HotelVO hotelVO_Error = new HotelVO();
        hotelVO_Error.setId(100);
        hotelVO_Error.setName("test_name");
        hotelVO_Error.setAddress("test_address");
        hotelVO_Error.setBizRegion("test_bizRegion");
        hotelVO_Error.setHotelStar("test_hotelStar");
        hotelVO_Error.setRate(5.0);
        hotelVO_Error.setDescription("test_description");
        hotelVO_Error.setPhoneNum("test_phoneNum");
        hotelVO_Error.setManagerId(7);
        assertFalse(hotelService.addHotel(hotelVO_Error).getSuccess());

        HotelVO hotelVO_Error2 = new HotelVO();
        hotelVO_Error2.setId(100);
        hotelVO_Error2.setName("test_name");
        hotelVO_Error2.setAddress("test_address");
        hotelVO_Error2.setBizRegion("test_bizRegion");
        hotelVO_Error2.setHotelStar("test_hotelStar");
        hotelVO_Error2.setRate(5.0);
        hotelVO_Error2.setDescription("test_description");
        hotelVO_Error2.setPhoneNum("test_phoneNum");
        hotelVO_Error2.setManagerId(-5);
        assertFalse(hotelService.addHotel(hotelVO_Error2).getSuccess());


    }

    @Test
    @Transactional
    @Rollback
    public void updateRoomInfo() {
        //正常
        Integer hotelId_Normal = 1;
        String roomType_Normal = "大床房";
        Integer rooms_Normal = 100;
        assertTrue(hotelService.updateRoomInfo(hotelId_Normal, roomType_Normal, rooms_Normal).getSuccess());

        //异常
        Integer hotelId_Error = -1;
        String roomType_Error = "大床房";
        Integer rooms_Error = -100;
        assertFalse(hotelService.updateRoomInfo(hotelId_Error, roomType_Error, rooms_Error).getSuccess());

    }

    @Test
    public void retrieveHotels() {
        hotelService.retrieveHotels().forEach(hotelVO -> System.out.println(hotelVO.toString()));

    }

    @Test
    public void retrieveHotelDetails() {
        int hotel_Normal = 1;
        assertNotNull(hotelService.retrieveHotelDetails(hotel_Normal));
        int hotel_Error = -1;
        assertNull(hotelService.retrieveHotelDetails(hotel_Error));

    }

    @Test
    public void getRoomCurNum() {
        Integer hotelId = 1;
        String roomType = "BigBed";
        System.out.println(hotelService.getRoomCurNum(hotelId, roomType));
    }

    @Test
    @Transactional
    @Rollback
    public void changeHotelRate() {
        //正常
        Integer orderId_Normal = 14;
        Integer hotelId_Normal = 3;
        Double rate_Normal = 5.0;
        assertTrue(hotelService.changeHotelRate(orderId_Normal, hotelId_Normal, rate_Normal).getSuccess());

        //异常
        Integer orderId_Error = -2;
        Integer hotelId_Error = 3;
        Double rate_Error = 5.0;
        assertFalse(hotelService.changeHotelRate(orderId_Error, hotelId_Error, rate_Error).getSuccess());
    }

    @Test
    @Transactional
    @Rollback
    public void updateHotelInfo() {
        //正常
        int hotelId_Normal = 1;
        String hotelAddress_Normal = "test_address";
        String bizRegion_Normal = "test_bizRegion";
        String hotelStar_Normal = "test_star";
        String hotelDescription_Normal = "test_description";
        assertTrue(hotelService.updateHotelInfo(hotelId_Normal, hotelAddress_Normal, bizRegion_Normal, hotelStar_Normal, hotelDescription_Normal).getSuccess());

        //异常
        int hotelId_Error = -2;
        String hotelAddress_Error = "test_address";
        String bizRegion_Error = "test_bizRegion";
        String hotelStar_Error = "test_star";
        String hotelDescription_Error = "test_description";
        assertFalse(hotelService.updateHotelInfo(hotelId_Error, hotelAddress_Error, bizRegion_Error, hotelStar_Error, hotelDescription_Error).getSuccess());
    }

}