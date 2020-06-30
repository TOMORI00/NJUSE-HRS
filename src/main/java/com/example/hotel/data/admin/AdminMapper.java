package com.example.hotel.data.admin;

import com.example.hotel.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenyizong
 */
@Mapper
@Repository
public interface AdminMapper {

    /**
     * 添加酒店工作人员
     *
     * @param user userPO
     * @return int
     */
    int addStaff(User user);

    /**
     * 添加网站营销人员
     *
     * @param user userPO
     * @return int
     */
    int addBusi(User user);

    /**
     * 获取所有酒店工作人员
     *
     * @return 所有酒店工作人员列表
     */
    List<User> getAllStaffs();

    /**
     * 获取所有网站营销人员
     *
     * @return 所有网站营销人员列表
     */
    List<User> getAllBusis();

    /**
     * 获取所有客户
     *
     * @return 所有客户列表
     */
    List<User> getAllClients();
}
