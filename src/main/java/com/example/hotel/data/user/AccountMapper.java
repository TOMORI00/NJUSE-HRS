package com.example.hotel.data.user;

import com.example.hotel.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Daiqj
 */
@Mapper
@Repository
public interface AccountMapper {

    /**
     * 创建一个新的账号
     *
     * @param user 用户
     * @return int
     */
    int createNewAccount(User user);

    /**
     * 根据用户名查找账号
     *
     * @param email 邮箱
     * @return User
     */
    User getAccountByName(@Param("email") String email);

    /**
     * 根据用ID查找账号
     *
     * @param id 用户ID
     * @return User
     */
    User getAccountById(@Param("id") int id);

    /**
     * 更新用户信息
     *
     * @param id          用户ID
     * @param password    密码
     * @param username    用户名
     * @param phonenumber 电话号码
     * @return int
     */
    int updateAccount(@Param("id") int id, @Param("password") String password, @Param("userName") String username, @Param("phoneNumber") String phonenumber);
}