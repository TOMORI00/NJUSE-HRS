package com.example.hotel.blimpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Biadlo
 */
@Service
public class TimeCouponStrategyImpl implements CouponMatchStrategy {

    /**
     * 判断某个订单是否满足某种限时优惠策略
     * 知道优惠券的起始YYYY-MM-DD，结束YYYY-MM-DD
     * 根据下单时的调用时间判断是否match
     *
     * @param orderVO 订单VO
     * @return boolean
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        int timeCouponIndex = 4;
        if (coupon.getCouponType() == timeCouponIndex) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime start = LocalDateTime.parse(orderVO.getCheckInDate() + " 00:00:00", df);
            LocalDateTime end = LocalDateTime.parse(orderVO.getCheckOutDate() + " 23:59:59", df);
            return coupon.getStartTime().isBefore(start) && coupon.getEndTime().isAfter(end);
        } else {
            return false;
        }
    }
}