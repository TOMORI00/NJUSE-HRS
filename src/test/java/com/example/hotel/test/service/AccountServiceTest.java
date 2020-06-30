package com.example.hotel.test.service;

import com.example.hotel.bl.user.AccountService;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.User;
import com.example.hotel.test.BasicTest;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

public class AccountServiceTest extends BasicTest {

    @Autowired
    private AccountService accountService;

    @Test
    @Transactional
    @Rollback
    public void registerAccount() {
        UserVO userVO_Normal = new UserVO();
        userVO_Normal.setId(100);
        userVO_Normal.setEmail("test_register@qq.com");
        userVO_Normal.setPassword("123456");
        userVO_Normal.setUserName("test_register");
        userVO_Normal.setPhoneNumber("1111111111");
        userVO_Normal.setCredit(100);
        userVO_Normal.setUserType(UserType.Client);
        ResponseVO responseVO_Normal = accountService.registerAccount(userVO_Normal);
        assertTrue(responseVO_Normal.getSuccess());

        UserVO userVO_Error = new UserVO();
        userVO_Error.setId(-1);
        userVO_Error.setEmail("test_register@qq.com");
        userVO_Error.setPassword("123456");
        userVO_Error.setUserName("test_register");
        userVO_Error.setPhoneNumber("1111111111");
        userVO_Error.setCredit(100);
        userVO_Error.setUserType(UserType.Client);
        ResponseVO responseVO_Error = accountService.registerAccount(userVO_Error);
        assertFalse(responseVO_Error.getSuccess());

    }

    @Test
    public void login() {
        //正常登录
        UserForm userForm_Normal = new UserForm();
        userForm_Normal.setEmail("staff@qq.com");
        userForm_Normal.setPassword("123456");
        assertNotNull(accountService.login(userForm_Normal));

        //无法登录
        UserForm userForm_Error = new UserForm();
        userForm_Error.setEmail("test@qq.com");
        userForm_Error.setPassword("123456");
        assertNull(accountService.login(userForm_Error));

    }

    @Test
    public void getUserInfo() {
        //可查询到
        int id_Normal = 4;
        User user_Normal = accountService.getUserInfo(id_Normal);
        assertNotNull(user_Normal);

        //查询不到
        int id_Error = 100;
        User user_Error = accountService.getUserInfo(id_Error);
        assertNull(user_Error);

    }

    @Test
    @Transactional
    @Rollback
    public void updateUserInfo() {
        //正常更新
        int id_Normal = 4;
        String password_Normal = "123456";
        String username_Normal = "test_Normal";
        String phonenumber_Normal = "1111";
        assertTrue(accountService.updateUserInfo(id_Normal, password_Normal, username_Normal, phonenumber_Normal).getSuccess());

        //更新异常
        int id_Error = -1;
        String password_Error = "123456";
        String username_Error = "test_Error";
        String phonenumber_Error = "0000";
        assertFalse(accountService.updateUserInfo(id_Error, password_Error, username_Error, phonenumber_Error).getSuccess());

    }

    @Test
    public void getCreditRecord() {
        //正常
        int id_Normal = 4;
        assertNotNull(accountService.getCreditRecord(id_Normal));

        //异常
        int id_Error = 100;
        assertNotNull(accountService.getCreditRecord(id_Error));

    }


    @Test
    @Transactional
    @Rollback
    public void rechargeUserCredit() {
        //正常
        double changeCredit_Normal = 100.0;
        int userId_Normal = 4;
        assertTrue(accountService.rechargeUserCredit(changeCredit_Normal, userId_Normal).getSuccess());

        //异常
        double changeCredit_Error = 100.0;
        int userId_Error = -1;
        assertFalse(accountService.rechargeUserCredit(changeCredit_Error, userId_Error).getSuccess());


    }
}