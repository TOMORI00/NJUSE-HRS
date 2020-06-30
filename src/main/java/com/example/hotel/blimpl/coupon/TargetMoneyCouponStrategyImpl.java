package com.example.hotel.blimpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.springframework.stereotype.Service;

/**
 * @author Daiqj
 */
@Service
public class TargetMoneyCouponStrategyImpl implements CouponMatchStrategy {

    /**
     * 判断某个订单是否满足某种满减金额优惠策略
     *
     * @param orderVO 订单VO
     * @param coupon  优惠券VO
     * @return boolean
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        return coupon.getCouponType() == 3 && orderVO.getPrice() >= coupon.getTargetMoney();
    }
}