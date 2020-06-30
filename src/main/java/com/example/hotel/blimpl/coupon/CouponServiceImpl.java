package com.example.hotel.blimpl.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelTargetMoneyCouponVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.TimeCouponVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Daiqj
 */
@Service
public class CouponServiceImpl implements CouponService {

    private final TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy;
    private final TimeCouponStrategyImpl timeCouponStrategy;
    private final CouponMapper couponMapper;
    private static List<CouponMatchStrategy> strategyList = new ArrayList<>();

    @Autowired
    public CouponServiceImpl(TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy,
                             TimeCouponStrategyImpl timeCouponStrategy,
                             CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
        this.targetMoneyCouponStrategy = targetMoneyCouponStrategy;
        this.timeCouponStrategy = timeCouponStrategy;
        strategyList.add(targetMoneyCouponStrategy);
        strategyList.add(timeCouponStrategy);
    }

    @Override
    public List<Coupon> getMatchOrderCoupon(OrderVO orderVO) {
        List<Coupon> hotelCoupons = getHotelAllCoupon(orderVO.getHotelId());
        List<Coupon> websiteCoupons = getHotelAllCoupon(-1);
        hotelCoupons.addAll(websiteCoupons);
        List<Coupon> availAbleCoupons = new ArrayList<>();
        for (Coupon hotelCoupon : hotelCoupons) {
            for (CouponMatchStrategy strategy : strategyList) {
                if (strategy.isMatch(orderVO, hotelCoupon)) {
                    availAbleCoupons.add(hotelCoupon);
                }
            }
        }
        return availAbleCoupons;
    }

    @Override
    public List<Coupon> getHotelAllCoupon(Integer hotelId) {
        return couponMapper.selectByHotelId(hotelId);
    }

    @Override
    public CouponVO addHotelTargetMoneyCoupon(HotelTargetMoneyCouponVO couponVO) {
        Coupon coupon = new Coupon();
        coupon.setCouponName(couponVO.getName());
        coupon.setDescription(couponVO.getDescription());
        coupon.setCouponType(couponVO.getType());
        coupon.setTargetMoney(couponVO.getTargetMoney());
        coupon.setHotelId(couponVO.getHotelId());
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        coupon.setStatus(1);
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public CouponVO addHotelTimeCoupon(TimeCouponVO couponVO) {
        Coupon coupon = new Coupon();
        try {
            coupon.setCouponName(couponVO.getName());
            coupon.setDescription(couponVO.getDescription());
            coupon.setCouponType(couponVO.getType());
            coupon.setHotelId(couponVO.getHotelId());
            coupon.setStatus(1);
            String startTimeStr = couponVO.getStartTime() + " 00:00:00";
            String endTimeStr = couponVO.getEndTime() + " 23:59:59";
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime startTime = LocalDateTime.parse(startTimeStr, df);
            LocalDateTime endTime = LocalDateTime.parse(endTimeStr, df);
            coupon.setEndTime(endTime);
            coupon.setStartTime(startTime);
            coupon.setDiscountMoney(couponVO.getDiscountMoney());
            int result = couponMapper.insertCoupon(coupon);
            couponVO.setId(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return couponVO;
    }
}