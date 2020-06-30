package com.example.hotel.test.service;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.po.User;
import com.example.hotel.test.BasicTest;
import com.example.hotel.vo.UserForm;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

public class AdminServiceTest extends BasicTest {

    @Autowired
    private AdminService adminService;

    @Test
    @Transactional
    @Rollback
    public void addStaff() {
        //正常
        UserForm userForm_Normal = new UserForm();
        userForm_Normal.setEmail("test@qq.com");
        userForm_Normal.setPassword("123456");
        assertTrue(adminService.addStaff(userForm_Normal).getSuccess());

        //异常
        UserForm userForm_Error = new UserForm();
        userForm_Error.setEmail("staff@qq.com");
        userForm_Error.setPassword("123456");
        assertFalse(adminService.addStaff(userForm_Error).getSuccess());

    }

    @Test
    public void getAllStaffs() {
        List<User> allStaffs = adminService.getAllStaffs();
        allStaffs.forEach(user -> System.out.println(user.toString()));
        assertNotNull(allStaffs);
    }

    @Test
    @Transactional
    @Rollback
    public void addBusi() {
        //正常
        UserForm userForm_Normal = new UserForm();
        userForm_Normal.setEmail("test@qq.com");
        userForm_Normal.setPassword("123456");
        assertTrue(adminService.addStaff(userForm_Normal).getSuccess());

        //异常
        UserForm userForm_Error = new UserForm();
        userForm_Error.setEmail("staff@qq.com");
        userForm_Error.setPassword("123456");
        assertFalse(adminService.addStaff(userForm_Error).getSuccess());
    }

    @Test
    public void getAllBusis() {
        List<User> allBusis = adminService.getAllBusis();
        allBusis.forEach(user -> System.out.println(user.toString()));
    }

    @Test
    public void getALLClients() {
        List<User> allClients = adminService.getAllClients();
        allClients.forEach(client -> System.out.println(client.toString()));
    }

    @Test
    @Transactional
    @Rollback
    public void getHotelIdByStaffId() {
        int staffId_Normal = 1;
        System.out.println(adminService.getHotelIdByStaffId(staffId_Normal));

        int staffId_Error = -1;
        System.out.println(adminService.getHotelIdByStaffId(staffId_Error));
    }
}