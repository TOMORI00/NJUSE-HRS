package com.example.hotel.test.controller;

import com.alibaba.fastjson.JSON;
import com.example.hotel.test.BasicTest;
import com.example.hotel.vo.UserInfoVO;
import com.example.hotel.vo.UserVO;
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

public class AccountControllerTest extends BasicTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void login() {
        RequestBuilder request_Normal = post("http://localhost/api/user/login").param("email", "333@qq.com").param("password", "123456");
        RequestBuilder request_Error = post("http://localhost/api/user/login").param("email", "test@qq.com").param("password", "123456");
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
    public void registerAccount() {
        UserVO userVO_Normal = new UserVO();
        userVO_Normal.setEmail("test@qq.com");
        userVO_Normal.setPassword("123456");
        userVO_Normal.setUserName("test");
        userVO_Normal.setPhoneNumber("123456789");
        String content_Normal = JSON.toJSONString(userVO_Normal);
        RequestBuilder request_Normal = post("http://localhost/api/user/register").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Normal);


        UserVO userVO_Error = new UserVO();
        userVO_Error.setEmail("test@qq.com");
        userVO_Error.setPassword("123456");
        userVO_Error.setUserName("test");
        userVO_Error.setPhoneNumber("123456789");
        String content_Error = JSON.toJSONString(userVO_Error);
        RequestBuilder request_Error = post("http://localhost/api/user/register").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Error);


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
    public void getUserInfo() {
        RequestBuilder request_Normal = get("http://localhost/api/user/4/getUserInfo");
        RequestBuilder request_Error = get("http://localhost/api/user/-1/getUserInfo");
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
    public void updateInfo() {
        UserInfoVO userInfoVO_Normal = new UserInfoVO();
        userInfoVO_Normal.setPassword("654321");
        userInfoVO_Normal.setPhoneNumber("110");
        userInfoVO_Normal.setUserName("test_normal");

        UserInfoVO userInfoVO_Error = new UserInfoVO();
        userInfoVO_Error.setPassword("654321");
        userInfoVO_Error.setPhoneNumber("110");
        userInfoVO_Error.setUserName("test_normal");

        String content_Normal = JSON.toJSONString(userInfoVO_Normal);
        String content_Error = JSON.toJSONString(userInfoVO_Error);


        RequestBuilder request_Normal = post("http://localhost/api/user/4/userInfo/update").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Normal);
        RequestBuilder request_Error = post("http://localhost/api/user/-1/userInfo/update").contentType(MediaType.APPLICATION_JSON_UTF8).content(content_Error);
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
    public void getCreditRecord() {
        RequestBuilder request_Normal = get("http://localhost/api/user/4/getCreditRecord");
        RequestBuilder request_Error = get("http://localhost/api/user/-1/getCreditRecord");
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