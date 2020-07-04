package com.example.hotel.test.Stubs.DataStubs;

import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.po.Coupon;

import java.util.LinkedList;
import java.util.List;

public class CouponMapperStub implements CouponMapper {
    @Override
    public int insertCoupon(Coupon coupon) {
        return 0;
    }

    @Override
    public List<Coupon> selectByHotelId(Integer hotelId) {
        LinkedList<Coupon> coupons = new LinkedList<>();
        Coupon coupon = new Coupon();
        coupon.setId(100);
        coupons.add(coupon);
        return coupons;
    }
}
