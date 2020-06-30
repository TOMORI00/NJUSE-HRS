package com.example.hotel.test.controller;

import com.alibaba.fastjson.JSON;
import com.example.hotel.test.BasicTest;
import com.example.hotel.vo.HotelTargetMoneyCouponVO;
import com.example.hotel.vo.TimeCouponVO;
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

public class CouponControllerTest extends BasicTest {

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
    public void addHotelTargetMoneyCoupon() {

        HotelTargetMoneyCouponVO couponVO_Normal = new HotelTargetMoneyCouponVO();
        couponVO_Normal.setName("test_name");
        couponVO_Normal.setDescription("test_description");
        couponVO_Normal.setType(1);
        couponVO_Normal.setTargetMoney(100);
        couponVO_Normal.setHotelId(-5); //
        couponVO_Normal.setDiscountMoney(20);

        String content_Normal = JSON.toJSONString(couponVO_Normal);

        HotelTargetMoneyCouponVO couponVO_Error = new HotelTargetMoneyCouponVO();
        couponVO_Error.setName("test_name2");
        couponVO_Error.setDescription("test_description2");
        couponVO_Error.setType(1);
        couponVO_Error.setTargetMoney(100);
        couponVO_Error.setHotelId(1); //
        couponVO_Error.setDiscountMoney(20);

        String content_Error = JSON.toJSONString(couponVO_Normal);

        RequestBuilder request_Normal = post("http://localhost/api/coupon//hotelTargetMoney").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Normal);
        RequestBuilder request_Error = post("http://localhost/api/coupon//hotelTargetMoney").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Error);
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
    public void addHotelTimeCoupon() { //hotelId

        TimeCouponVO couponVO_Normal = new TimeCouponVO();
        couponVO_Normal.setName("test_name");
        couponVO_Normal.setDescription("test_description");
        couponVO_Normal.setType(2);
        couponVO_Normal.setHotelId(1);
        couponVO_Normal.setStartTime("2020-05-20");
        couponVO_Normal.setEndTime("2020-05-21");

        String content_Normal = JSON.toJSONString(couponVO_Normal);

        TimeCouponVO couponVO_Error = new TimeCouponVO();
        couponVO_Error.setName("test_name");
        couponVO_Error.setDescription("test_description");
        couponVO_Error.setType(2);
        couponVO_Error.setHotelId(-5);
        couponVO_Error.setStartTime("2020-05-20");
        couponVO_Error.setEndTime("2020-05-21");

        String content_Error = JSON.toJSONString(couponVO_Normal);

        RequestBuilder request_Normal = post("http://localhost/api/coupon//hotelTime").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Normal);
        RequestBuilder request_Error = post("http://localhost/api/coupon//hotelTime").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Error);
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
    public void getHotelAllCoupons() {
        RequestBuilder request_Normal = get("http://localhost/api/coupon/hotelAllCoupons").param("hotelId", "1");
        try {
            String response_Normal = mvc.perform(request_Normal).andReturn().getResponse().getContentAsString();
            System.out.println(response_Normal);
            assertNotNull(response_Normal);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void getOrderMatchCoupons() {

        RequestBuilder request_Normal = get("http://localhost/api/coupon/orderMatchCoupons")
                .param("hotelId", "1")
                .param("orderPrice", "1000.0")
                .param("roomNum", "4")
                .param("checkIn", "2020-5-20")
                .param("checkOut", "2020-5-21");

        try {
            String response_Normal = mvc.perform(request_Normal).andReturn().getResponse().getContentAsString();
            System.out.println(response_Normal);
            assertNotNull(response_Normal);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}