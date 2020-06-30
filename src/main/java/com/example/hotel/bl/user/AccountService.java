package com.example.hotel.bl.user;

import com.example.hotel.po.CreditRecord;
import com.example.hotel.po.User;
import com.example.hotel.vo.*;

import java.util.List;

/**
 * @author huwen
 */
public interface AccountService {

    /**
     * 注册账号
     *
     * @param userVO 用户VO
     * @return ResponseVO
     */
    ResponseVO registerAccount(UserVO userVO);

    /**
     * 用户登录，登录成功会将用户信息保存再session中
     *
     * @param userForm 用户登录VO
     * @return User
     */
    User login(UserForm userForm);

    /**
     * 获取用户个人信息
     *
     * @param id 用户ID
     * @return User
     */
    User getUserInfo(int id);

    /**
     * 更新用户个人信息
     *
     * @param id          用户ID
     * @param password    密码
     * @param username    用户名
     * @param phonenumber 电话号码
     * @return ResponseVO
     */
    ResponseVO updateUserInfo(int id, String password, String username, String phonenumber);

    /**
     * 获取某个用户的信用记录列表
     *
     * @param userId 用户ID
     * @return 信用记录列表
     */
    List<CreditRecord> getCreditRecord(Integer userId);

    /**
     * 为用户进行信用充值
     *
     * @param changeCredit 信用改变量
     * @param userId       用户id
     * @return ResponseVO
     */
    ResponseVO rechargeUserCredit(double changeCredit, int userId);
}