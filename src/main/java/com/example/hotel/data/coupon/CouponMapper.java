package com.example.hotel.data.coupon;

import com.example.hotel.po.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Daiqj
 */
@Mapper
@Repository
public interface CouponMapper {

    /**
     * 添加优惠券
     *
     * @param coupon 优惠券PO
     * @return int
     */
    int insertCoupon(Coupon coupon);

    /**
     * 按照酒店ID获取优惠券
     *
     * @param hotelId 酒店ID
     * @return 优惠券列表
     */
    List<Coupon> selectByHotelId(Integer hotelId);
}