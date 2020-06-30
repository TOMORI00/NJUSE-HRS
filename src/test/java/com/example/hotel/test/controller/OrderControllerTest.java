package com.example.hotel.test.controller;

import com.alibaba.fastjson.JSON;
import com.example.hotel.test.BasicTest;
import com.example.hotel.vo.OrderVO;
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

public class OrderControllerTest extends BasicTest {

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
    public void reserveHotel() {  //有bug
        OrderVO orderVO_Normal = new OrderVO();
        orderVO_Normal.setId(1);
        orderVO_Normal.setUserId(7);
        orderVO_Normal.setHotelId(1);
        orderVO_Normal.setHotelName("汉庭酒店");
        orderVO_Normal.setCheckInDate("2020-05-20");
        orderVO_Normal.setCheckOutDate("2020-05-21");
        orderVO_Normal.setRoomType("BigBed");
        orderVO_Normal.setRoomNum(3);
        orderVO_Normal.setPeopleNum(3);
        orderVO_Normal.setHaveChild(true);
        orderVO_Normal.setCreateDate("2020-05-11");
        orderVO_Normal.setPrice(1000.0);
        orderVO_Normal.setClientName("staff1");
        orderVO_Normal.setPhoneNumber("1111");
        orderVO_Normal.setOrderState("已预定");

        String content_Normal = JSON.toJSONString(orderVO_Normal);

        OrderVO orderVO_Error = new OrderVO();
        orderVO_Error.setId(1);
        orderVO_Error.setUserId(-5);
        orderVO_Error.setHotelId(1);
        orderVO_Error.setHotelName("汉庭酒店");
        orderVO_Error.setCheckInDate("2020-05-20");
        orderVO_Error.setCheckOutDate("2020-05-21");
        orderVO_Error.setRoomType("BigBed");
        orderVO_Error.setRoomNum(3);
        orderVO_Error.setPeopleNum(3);
        orderVO_Error.setHaveChild(true);
        orderVO_Error.setCreateDate("2020-05-11");
        orderVO_Error.setPrice(1000.0);
        orderVO_Error.setClientName("1");
        orderVO_Error.setPhoneNumber("1111");
        orderVO_Error.setOrderState("已预定");

        String content_Error = JSON.toJSONString(orderVO_Normal);

        RequestBuilder request_Normal = post("http://localhost/api/order/addOrder").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Normal);
        RequestBuilder request_Error = post("http://localhost/api/order/addOrder").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Error);

        try {
            //正常
            String response_Normal = mvc.perform(request_Normal).andReturn().getResponse().getContentAsString();
            System.out.println(response_Normal);
            assertNotNull(response_Normal);
            //异常
            String response_Error = mvc.perform(request_Normal).andReturn().getResponse().getContentAsString();
            System.out.println(response_Error);
            assertNotNull(response_Error);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void retrieveAllOrders() {
        RequestBuilder request_Normal = get("http://localhost/api/order//getAllOrders");

        try {
            String response_Normal = mvc.perform(request_Normal).andReturn().getResponse().getContentAsString();
            System.out.println(response_Normal);
            assertNotNull(response_Normal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void retrieveUserOrders() {
        RequestBuilder request_Normal = get("http://localhost/api/hotel/{userid}/getUserOrders", 1);

        RequestBuilder request_Error = get("http://localhost/api/hotel/{userid}/getUserOrders", -1);
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
    public void annulOrder() {
        RequestBuilder request_Normal = get("http://localhost/api/hotel/{orderid}/annulOrder", 1);

        RequestBuilder request_Error = get("http://localhost/api/hotel/{orderid}/annulOrder", -1);
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
    public void restoreOrder() {
        RequestBuilder request_Normal = get("http://localhost/api/hotel/{orderid}/restoreOrder", 1);

        RequestBuilder request_Error = get("http://localhost/api/hotel/{orderid}/restoreOrder", -1);
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
    public void execOrder() {
        RequestBuilder request_Normal = get("http://localhost/api/hotel/{orderid}/checkIn", 1);

        RequestBuilder request_Error = get("http://localhost/api/hotel/{orderid}/checkIn", -1);
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
    public void getHotelOrdersByStaffId() {
        RequestBuilder request_Normal = get("http://localhost/api/hotel/{userid}/getHotelOrdersByStaffId", 1);

        RequestBuilder request_Error = get("http://localhost/api/hotel/{userid}/getHotelOrdersByStaffId", -1);
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