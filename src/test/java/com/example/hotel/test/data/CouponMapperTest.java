package com.example.hotel.test.data;

import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.test.BasicTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

public class CouponMapperTest extends BasicTest {
    @Autowired
    private CouponMapper couponMapper;

    @Test
    @Transactional
    @Rollback
    //添加满减优惠卷
    public void insertCouponTest1() {
        Coupon coupon = new Coupon();
        coupon.setCouponName("添加满减优惠卷测试1");
        coupon.setCouponType(3);
        coupon.setHotelId(1);
        coupon.setDescription("添加满减优惠卷测试1");
        coupon.setTargetMoney(100);
        coupon.setDiscountMoney(50);
        coupon.setStatus(1);
        try {
            couponMapper.insertCoupon(coupon);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Transactional
    @Rollback
    //添加限时优惠卷
    public void insertCouponTest2() {
        Coupon coupon = new Coupon();
        coupon.setCouponName("添加限时优惠卷测试1");
        coupon.setCouponType(4);
        coupon.setHotelId(1);
        coupon.setDescription("添加限时优惠卷测试1");
        coupon.setDiscountMoney(50);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime beginTime = LocalDateTime.parse("2020-06-16 00:00:00", df);
        LocalDateTime endTime = LocalDateTime.parse("2020-06-20 00:00:00", df);
        coupon.setStartTime(beginTime);
        coupon.setEndTime(endTime);
        coupon.setStatus(1);
        try {
            couponMapper.insertCoupon(coupon);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    //正常选择
    public void selectByHotelIdTest1() {
        List<Coupon> couponList = couponMapper.selectByHotelId(2);
        for (Coupon coupon : couponList) {
            System.out.println(coupon.toString());
        }
        assertNotNull(couponList);
    }

    @Test
    //选择不存在的酒店
    public void selectByHotelIdTest2() {
        List<Coupon> couponList = couponMapper.selectByHotelId(-100);
        assertEquals(0, couponList.size());
    }

    @Test
    //选择网站优惠卷
    public void selectByHotelIdTest3() {
        List<Coupon> couponList = couponMapper.selectByHotelId(-1);
        for (Coupon coupon : couponList) {
            if (coupon.getHotelId() != -1) {
                fail();
            }
        }
    }

}
