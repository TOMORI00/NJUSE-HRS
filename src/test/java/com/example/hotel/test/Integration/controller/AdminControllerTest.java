package com.example.hotel.test.Integration.controller;

import com.alibaba.fastjson.JSON;
import com.example.hotel.test.BasicTest;
import com.example.hotel.vo.UserForm;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class AdminControllerTest extends BasicTest {
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
    public void addStaff() {

        UserForm staff_Normal = new UserForm();
        staff_Normal.setEmail("test@qq.com");
        staff_Normal.setPassword("654321");
        String content_Normal = JSON.toJSONString(staff_Normal);

        UserForm staff_Error = new UserForm();
        staff_Error.setEmail("333@qq.com");
        staff_Error.setPassword("654321");
        String content_Error = JSON.toJSONString(staff_Error);

        RequestBuilder request_Normal = post("http://localhost/api/admin/addStaff").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Normal);
        RequestBuilder request_Error = post("http://localhost/api/admin/addStaff").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Error);
        try {
            //正常
            String response_Normal = mvc.perform(request_Normal).andReturn().getResponse().getContentAsString();
            System.out.println(response_Normal);
            //异常
            String response_Error = mvc.perform(request_Error).andReturn().getResponse().getContentAsString();
            System.out.println(response_Error);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Transactional
    @Rollback
    public void addBusi() {
        UserForm busi_Normal = new UserForm();
        busi_Normal.setEmail("test@qq.com");
        busi_Normal.setPassword("654321");
        String content_Normal = JSON.toJSONString(busi_Normal);

        UserForm busi_Error = new UserForm();
        busi_Error.setEmail("333@qq.com");
        busi_Error.setPassword("654321");
        String content_Error = JSON.toJSONString(busi_Error);

        RequestBuilder request_Normal = post("http://localhost/api/admin/addBusi").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Normal);
        RequestBuilder request_Error = post("http://localhost/api/admin/addBusi").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Error);
        try {
            //正常
            String response_Normal = mvc.perform(request_Normal).andReturn().getResponse().getContentAsString();
            System.out.println(response_Normal);
            //异常
            String response_Error = mvc.perform(request_Error).andReturn().getResponse().getContentAsString();
            System.out.println(response_Error);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllStaffs() {
        RequestBuilder request = post("http://localhost/api/admin/getAllStaffs");
        try {
            String response = mvc.perform(request).andReturn().getResponse().getContentAsString();
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllBusis() {
        RequestBuilder request = post("http://localhost/api/admin/getAllBusis");
        try {
            String response = mvc.perform(request).andReturn().getResponse().getContentAsString();
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}