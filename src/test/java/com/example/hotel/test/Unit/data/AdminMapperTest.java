package com.example.hotel.test.Unit.data;

import com.example.hotel.data.admin.AdminMapper;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.User;
import com.example.hotel.test.BasicTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

public class AdminMapperTest extends BasicTest {
    @Autowired
    private AdminMapper adminMapper;

    @Test
    @Transactional
    @Rollback
    //正常添加酒店工作人员
    public void addStaffTest1() {
        User user = new User();
        user.setEmail("addStaffTest1@qq.com");
        user.setPassword("123456");
        user.setUserType(UserType.Staff);
        try {
            adminMapper.addStaff(user);
        } catch (Exception e) {
            fail();
        }
    }

    //添加酒店工作人员的邮箱已存在
    @Test
    @Transactional
    @Rollback
    public void addStaffTest2() {
        User user = new User();
        user.setEmail("333@qq.com");
        user.setPassword("123456");
        user.setUserType(UserType.Staff);
        try {
            adminMapper.addStaff(user);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

    }

    @Test
    @Transactional
    @Rollback
    //正常添加网站营销人员
    public void addBusiTest1() {
        User user = new User();
        user.setEmail("addBusiTest1@qq.com");
        user.setPassword("123456");
        user.setUserType(UserType.Busi);
        try {
            adminMapper.addBusi(user);
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    @Transactional
    @Rollback
    //添加网站营销人员的邮箱已存在
    public void addBusiTest2() {
        User user = new User();
        user.setEmail("333@qq.com");
        user.setPassword("123456");
        user.setUserType(UserType.Busi);
        try {
            adminMapper.addBusi(user);
            fail();
        } catch (Exception e) {
            assertTrue(true);
        }

    }

    @Test
    public void getAllStaffsTest1() {
        List<User> userList = adminMapper.getAllStaffs();
        for (User user : userList) {
            System.out.println(user.toString());
        }
        assertNotNull(userList);
    }

    @Test
    public void getAllBusisTest1() {
        List<User> userList = adminMapper.getAllBusis();
        for (User user : userList) {
            System.out.println(user.toString());
        }
        assertNotNull(userList);

    }

}
