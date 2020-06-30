package com.example.hotel.controller.admin;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenyizong
 */
@RestController()
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/addStaff")
    public ResponseVO addStaff(@RequestBody UserForm userForm) {
        return adminService.addStaff(userForm);
    }

    @PostMapping("/addBusi")
    public ResponseVO addBusi(@RequestBody UserForm userForm) {
        return adminService.addBusi(userForm);
    }

    @PostMapping("/getAllStaffs")
    public ResponseVO getAllStaffs() {
        return ResponseVO.buildSuccess(adminService.getAllStaffs());
    }

    @PostMapping("/getAllBusis")
    public ResponseVO getAllBusis() {
        return ResponseVO.buildSuccess(adminService.getAllBusis());
    }

    @PostMapping("/getAllClient")
    public ResponseVO getAllClient() {
        return ResponseVO.buildSuccess(adminService.getAllClients());
    }

    @GetMapping("/{userId}/getChargeHotelIdByStaffId")
    public ResponseVO getChargeHotelIdByStaffId(@PathVariable int userId) {
        return ResponseVO.buildSuccess(adminService.getHotelIdByStaffId(userId));
    }

}