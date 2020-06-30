package com.example.hotel.bl.coupon;

import com.example.hotel.po.Coupon;

import com.example.hotel.vo.OrderVO;

/**
 * @author Daiqj
 */
public interface CouponMatchStrategy {

    /**
     * 判断优惠券是否匹配
     *
     * @param coupon  优惠券VO
     * @param orderVO 订单VO
     * @return boolean
     */
    boolean isMatch(OrderVO orderVO, Coupon coupon);

}