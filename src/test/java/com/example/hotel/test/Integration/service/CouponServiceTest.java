package com.example.hotel.test.Integration.service;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.blimpl.coupon.TimeCouponStrategyImpl;
import com.example.hotel.po.Coupon;
import com.example.hotel.test.BasicTest;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelTargetMoneyCouponVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.TimeCouponVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CouponServiceTest extends BasicTest {

    @Autowired
    private CouponService couponService;

    @Test
    public void getMatchOrderCoupon() {
        OrderVO orderVO = new OrderVO();
        orderVO.setId(1);
        orderVO.setUserId(4);
        orderVO.setHotelId(1);
        orderVO.setHotelName("汉庭酒店");
        orderVO.setCheckInDate("2020-06-01");
        orderVO.setCheckOutDate("2020-06-02");
        orderVO.setRoomType("BigBed");
        orderVO.setRoomNum(2);
        orderVO.setPeopleNum(2);
        orderVO.setHaveChild(true);
        orderVO.setCreateDate("2019-5-20");
        orderVO.setPrice(10000.00);
        orderVO.setClientName("test");
        orderVO.setPhoneNumber("1111");
        orderVO.setOrderState("已预定");
        List<Coupon> matchOrderCoupon = couponService.getMatchOrderCoupon(orderVO);
        matchOrderCoupon.forEach(coupon -> System.out.println(coupon.toString()));
    }

    @Test
    public void getHotelAllCoupon() {

        //可以查到
        int hotelId_Normal = 1;
        couponService.getHotelAllCoupon(hotelId_Normal).forEach(coupon -> System.out.println(coupon.toString()));


        //查不到
        int hotelId_Error = -1;
        couponService.getHotelAllCoupon(hotelId_Error).forEach(coupon -> System.out.println(coupon.toString()));

    }

    @Test
    @Transactional
    @Rollback
    public void addHotelTargetMoneyCoupon() {

        HotelTargetMoneyCouponVO coupon = new HotelTargetMoneyCouponVO();
        coupon.setName("test_name");
        coupon.setDescription("test_description");
        coupon.setType(1);
        coupon.setTargetMoney(100);
        coupon.setHotelId(1);
        coupon.setDiscountMoney(20);
        CouponVO result = couponService.addHotelTargetMoneyCoupon(coupon);
        assertNotNull(result);
        System.out.println(result.toString());

    }

    @Test
    @Transactional
    @Rollback
    public void addHotelTimeCoupon() {

        TimeCouponVO coupon = new TimeCouponVO();
        coupon.setName("test_name");
        coupon.setDescription("test_description");
        coupon.setType(2);
        coupon.setHotelId(1);
        coupon.setStartTime("2020-05-20");
        coupon.setEndTime("2020-05-21");
        coupon.setDiscountMoney(100);
        CouponVO result = couponService.addHotelTimeCoupon(coupon);
        assertNotNull(result);
        System.out.println(result.toString());

    }

    @Test
    public void isMatch() {
        OrderVO orderVO = new OrderVO();
        orderVO.setId(1);
        orderVO.setUserId(4);
        orderVO.setHotelId(1);
        orderVO.setHotelName("汉庭酒店");
        orderVO.setCheckInDate("2020-06-01");
        orderVO.setCheckOutDate("2020-06-02");
        orderVO.setRoomType("BigBed");
        orderVO.setRoomNum(2);
        orderVO.setPeopleNum(2);
        orderVO.setHaveChild(true);
        orderVO.setCreateDate("2019-5-20");
        orderVO.setPrice(10000.00);
        orderVO.setClientName("test");
        orderVO.setPhoneNumber("1111");
        orderVO.setOrderState("已预定");
        TimeCouponStrategyImpl timeCouponStrategy = new TimeCouponStrategyImpl();
        Coupon coupon = new Coupon();
        coupon.setCouponType(4);
        coupon.setCouponName("123");
        coupon.setHotelId(-1);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        coupon.setStartTime(LocalDateTime.parse("2020-04-30 00:00:00", df));
        coupon.setEndTime(LocalDateTime.parse("2020-07-05 00:00:00", df));
        assertTrue(timeCouponStrategy.isMatch(orderVO, coupon));

    }
}