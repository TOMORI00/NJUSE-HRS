package com.example.hotel.test.controller;

import com.alibaba.fastjson.JSON;
import com.example.hotel.enums.RoomType;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.test.BasicTest;
import com.example.hotel.vo.HotelInfoVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.RateVO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class HotelControllerTest extends BasicTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    @Transactional
    @Rollback
    public void createHotel() {

        HotelVO hotelVO_Normal = new HotelVO();
        hotelVO_Normal.setId(10);
        hotelVO_Normal.setName("test_name");
        hotelVO_Normal.setAddress("test_address");
        hotelVO_Normal.setBizRegion("test_bizRegion");
        hotelVO_Normal.setHotelStar("test_hotelStar");
        hotelVO_Normal.setRate(5.0);
        hotelVO_Normal.setDescription("test_description");
        hotelVO_Normal.setPhoneNum("test_phoneNum");
        hotelVO_Normal.setManagerId(1);

        String content_Normal = JSON.toJSONString(hotelVO_Normal);

        HotelVO hotelVO_Error = new HotelVO();
        hotelVO_Error.setId(10);
        hotelVO_Error.setName("test_name");
        hotelVO_Error.setAddress("test_address");
        hotelVO_Error.setBizRegion("test_bizRegion");
        hotelVO_Error.setHotelStar("test_hotelStar");
        hotelVO_Error.setRate(5.0);
        hotelVO_Error.setDescription("test_description");
        hotelVO_Error.setPhoneNum("test_phoneNum");
        hotelVO_Error.setManagerId(100);

        String content_Error = JSON.toJSONString(hotelVO_Error);


        RequestBuilder request_Normal = post("http://localhost/api/hotel/addHotel").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Normal);

        RequestBuilder request_Error = post("http://localhost/api/hotel/addHotel").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Error);
        try {
            //正常
            String response_Normal = mvc.perform(request_Normal).andReturn().getResponse().getContentAsString();
            System.out.println(response_Normal);
            assertNotNull(response_Normal);
            //异常
            String response_Error = mvc.perform(request_Error).andReturn().getResponse().getContentAsString();
            System.out.println(response_Error);
            assertNotNull(response_Error);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void retrieveAllHotels() {
        RequestBuilder request_Normal = get("http://localhost/api/hotel/all");

        try {
            String response_Normal = mvc.perform(request_Normal).andReturn().getResponse().getContentAsString();
            System.out.println(response_Normal);
            assertNotNull(response_Normal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback
    public void addRoomInfo() { // 相同返回异常

        HotelRoom hotelRoom_Normal = new HotelRoom();
        hotelRoom_Normal.setId(10);
        hotelRoom_Normal.setRoomType(RoomType.BigBed);
        hotelRoom_Normal.setHotelId(1);
        hotelRoom_Normal.setPrice(1000.0);
        hotelRoom_Normal.setCurNum(100);
        hotelRoom_Normal.setTotal(1000);

        String content_Normal = JSON.toJSONString(hotelRoom_Normal);

        HotelRoom hotelRoom_Error = new HotelRoom();
        hotelRoom_Error.setId(10);
        hotelRoom_Error.setRoomType(RoomType.BigBed);
        hotelRoom_Error.setHotelId(1);
        hotelRoom_Error.setPrice(1000.0);
        hotelRoom_Error.setCurNum(100);
        hotelRoom_Error.setTotal(1000);

        String content_Error = JSON.toJSONString(hotelRoom_Error);

        RequestBuilder request_Normal = post("http://localhost/api/hotel/roomInfo").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Normal);
        RequestBuilder request_Error = post("http://localhost/api/hotel/roomInfo").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Error);

        try {
            //正常
            String response_Normal = mvc.perform(request_Normal).andReturn().getResponse().getContentAsString();
            System.out.println(response_Normal);
            assertNotNull(response_Normal);
            //异常
            String response_Error = mvc.perform(request_Error).andReturn().getResponse().getContentAsString();
            System.out.println(response_Error);
            assertNotNull(response_Error);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void retrieveHotelDetail() {
        RequestBuilder request_normal = get("http://localhost/api/hotel/{hotelId}/detail", 1);

        RequestBuilder request_Error = get("http://localhost/api/hotel/{hotelId}/detail", -1);
        try {
            //正常
            String response_Normal = mvc.perform(request_normal).andReturn().getResponse().getContentAsString();
            System.out.println(response_Normal);
            assertNotNull(response_Normal);
            //异常
            String response_Error = mvc.perform(request_Error).andReturn().getResponse().getContentAsString();
            System.out.println(response_Error);
            assertNotNull(response_Error);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void retrieveHotelOrders() {
        RequestBuilder request_normal = get("http://localhost/api/hotel/{hotelId}/allOrders", 1);

        RequestBuilder request_Error = get("http://localhost/api/hotel/{hotelId}/allOrders", -1);
        try {
            //正常
            String response_Normal = mvc.perform(request_normal).andReturn().getResponse().getContentAsString();
            System.out.println(response_Normal);
            assertNotNull(response_Normal);
            //异常
            String response_Error = mvc.perform(request_Error).andReturn().getResponse().getContentAsString();
            System.out.println(response_Error);
            assertNotNull(response_Error);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Transactional
    @Rollback
    public void changeHotelRate() {
        RateVO rateVO_Normal = new RateVO();
        rateVO_Normal.setHotelId(1);
        rateVO_Normal.setRate(1.0);
        String content_Normal = JSON.toJSONString(rateVO_Normal);

        RateVO rateVO_Error = new RateVO();
        rateVO_Error.setHotelId(-1);
        rateVO_Error.setRate(1.0);
        String content_Error = JSON.toJSONString(rateVO_Error);

        RequestBuilder request_Normal = post("http://localhost/api/hotel/changeHotelRate").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Normal);

        RequestBuilder request_Error = post("http://localhost/api/hotel/changeHotelRate").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Error);
        try {
            //正常
            String response_Normal = mvc.perform(request_Normal).andReturn().getResponse().getContentAsString();
            System.out.println(response_Normal);
            assertNotNull(response_Normal);
            //异常
            String response_Error = mvc.perform(request_Error).andReturn().getResponse().getContentAsString();
            System.out.println(response_Error);
            assertNotNull(response_Error);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback
    public void getChargeHotelIdByStaffId() {
        RequestBuilder request_normal = get("http://localhost/api/hotel/{userid}/getChargeHotelIdByStaffId", 1);

        RequestBuilder request_Error = get("http://localhost/api/hotel/{userid}/getChargeHotelIdByStaffId", -1);
        try {
            //正常
            String response_Normal = mvc.perform(request_normal).andReturn().getResponse().getContentAsString();
            System.out.println(response_Normal);
            assertNotNull(response_Normal);
            //异常
            String response_Error = mvc.perform(request_Error).andReturn().getResponse().getContentAsString();
            System.out.println(response_Error);
            assertNotNull(response_Error);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    @Transactional
    @Rollback
    public void updateInfo() {

        HotelInfoVO hotelInfoVO_Normal = new HotelInfoVO();
        hotelInfoVO_Normal.setHotelName("test_normal");
        hotelInfoVO_Normal.setHotelLocation("test_location");
        hotelInfoVO_Normal.setPhoneNumber("test_phoneNum");
        hotelInfoVO_Normal.setDescription("test_description");
        hotelInfoVO_Normal.setHotelStar("test_star");
        hotelInfoVO_Normal.setBizRegion("test_bizRegion");

        String content_Normal = JSON.toJSONString(hotelInfoVO_Normal);

        HotelInfoVO hotelInfoVO_Error = new HotelInfoVO();
        hotelInfoVO_Error.setHotelName("test_normal");
        hotelInfoVO_Error.setHotelLocation("test_location");
        hotelInfoVO_Error.setPhoneNumber("test_phoneNum");
        hotelInfoVO_Error.setDescription("test_description");
        hotelInfoVO_Error.setHotelStar("test_star");
        hotelInfoVO_Error.setBizRegion("test_bizRegion");

        String content_Error = JSON.toJSONString(hotelInfoVO_Error);

        RequestBuilder request_Normal = post("http://localhost/api/hotel/{id}/hotelInfo/update", 1).contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Normal);

        RequestBuilder request_Error = post("http://localhost/api/hotel/{id}/hotelInfo/update", -1).contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Error);
        try {
            //正常
            String response_Normal = mvc.perform(request_Normal).andReturn().getResponse().getContentAsString();
            System.out.println(response_Normal);
            assertNotNull(response_Normal);
            //异常
            String response_Error = mvc.perform(request_Error).andReturn().getResponse().getContentAsString();
            System.out.println(response_Error);
            assertNotNull(response_Error);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}