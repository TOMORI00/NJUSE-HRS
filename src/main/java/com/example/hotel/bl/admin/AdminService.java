package com.example.hotel.bl.admin;

import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;

import java.util.List;

/**
 * @author chenyizong
 */
public interface AdminService {

    /**
     * 添加酒店管理人员账号
     *
     * @param userForm 用户VO
     * @return ResponseVO
     */
    ResponseVO addStaff(UserForm userForm);

    /**
     * 获得所有酒店工作人员信息
     *
     * @return 所有酒店工作人员列表
     */
    List<User> getAllStaffs();

    /**
     * 添加网站营销人员账号
     *
     * @param userForm 用户VO
     * @return ResponseVO
     */
    ResponseVO addBusi(UserForm userForm);

    /**
     * 获得所有网站营销人员信息
     *
     * @return 所有网站营销人员列表
     */
    List<User> getAllBusis();

    /**
     * 获取所有Client信息
     *
     * @return 所有客户列表
     */
    List<User> getAllClients();

    /**
     * 根据StaffId,返回对应hotelId,若该staff没绑定hotel，返回-1
     *
     * @param staffId 工作人员ID
     * @return int
     */
    int getHotelIdByStaffId(int staffId);
}