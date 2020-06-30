package com.example.hotel.controller.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;

/**
 * @author Daiqj
 */
@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/hotelTargetMoney")
    public ResponseVO addHotelTargetMoneyCoupon(@RequestBody HotelTargetMoneyCouponVO hotelTargetMoneyCouponVO) {
        System.out.println("======================添加满减优惠=======================");
        System.out.println("ID:" + hotelTargetMoneyCouponVO.getHotelId());
        System.out.println("NAME:" + hotelTargetMoneyCouponVO.getName());
        System.out.println("DES:" + hotelTargetMoneyCouponVO.getDescription());
        System.out.println("TAR:" + hotelTargetMoneyCouponVO.getTargetMoney());
        System.out.println("DIS:" + hotelTargetMoneyCouponVO.getDiscountMoney());
        System.out.println("TYPE:" + hotelTargetMoneyCouponVO.getType());
        System.out.println("=======================================================");
        CouponVO couponVO = couponService.addHotelTargetMoneyCoupon(hotelTargetMoneyCouponVO);
        return ResponseVO.buildSuccess(couponVO);
    }

    @PostMapping("/hotelTime")
    public ResponseVO addHotelTimeCoupon(@RequestBody TimeCouponVO timeCouponVO) {
        System.out.println("======================添加限时优惠=======================");
        System.out.println("ID:" + timeCouponVO.getHotelId());
        System.out.println("NAME:" + timeCouponVO.getName());
        System.out.println("DES:" + timeCouponVO.getDescription());
        System.out.println("DIS:" + timeCouponVO.getDiscountMoney());
        System.out.println("TYPE:" + timeCouponVO.getType());
        System.out.println("BEGIN:" + timeCouponVO.getStartTime());
        System.out.println("End:" + timeCouponVO.getEndTime());
        System.out.println("=======================================================");
        CouponVO couponVO = couponService.addHotelTimeCoupon(timeCouponVO);
        return ResponseVO.buildSuccess(couponVO);
    }

    @GetMapping("/hotelAllCoupons")
    public ResponseVO getHotelAllCoupons(@RequestParam Integer hotelId) {
        return ResponseVO.buildSuccess(couponService.getHotelAllCoupon(hotelId));
    }

    @GetMapping("/orderMatchCoupons")
    public ResponseVO getOrderMatchCoupons(@RequestParam Integer userId,
                                           @RequestParam Integer hotelId,
                                           @RequestParam Double orderPrice,
                                           @RequestParam Integer roomNum,
                                           @RequestParam String checkIn,
                                           @RequestParam String checkOut) {
        OrderVO requestOrderVO = new OrderVO();
        requestOrderVO.setUserId(userId);
        requestOrderVO.setHotelId(hotelId);
        requestOrderVO.setPrice(orderPrice);
        requestOrderVO.setRoomNum(roomNum);
        requestOrderVO.setCheckInDate(checkIn);
        requestOrderVO.setCheckOutDate(checkOut);
        System.out.println(orderPrice);
        return ResponseVO.buildSuccess(couponService.getMatchOrderCoupon(requestOrderVO));
    }
}