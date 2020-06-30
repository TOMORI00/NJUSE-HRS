package com.example.hotel.data.hotel;

import com.example.hotel.po.Hotel;
import com.example.hotel.vo.HotelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ljl
 */
@Mapper
@Repository
public interface HotelMapper {

    /**
     * 添加酒店
     *
     * @param hotel 酒店PO
     * @return int
     */
    int insertHotel(Hotel hotel);

    /**
     * 获取所有酒店
     *
     * @return 所有酒店
     */
    List<HotelVO> selectAllHotel();

    /**
     * 按照id获取酒店
     *
     * @param id 酒店ID
     * @return HotelVO
     */
    HotelVO selectById(@Param("id") Integer id);

    /**
     * 改变酒店评分
     *
     * @param hotelId 酒店ID
     * @param rate    评分
     */
    void changeHotelRate(Integer hotelId, Double rate);

    /**
     * 更新酒店信息
     *
     * @param hotelid          酒店ID
     * @param hotelAddress     酒店地址
     * @param bizRegion        商圈
     * @param hotelStar        酒店星级
     * @param hotelDescription 酒店描述
     * @return int
     */
    int updateHotelInfo(@Param("hotelid") Integer hotelid, @Param("hotelAddress") String hotelAddress, @Param("bizRegion") String bizRegion, @Param("hotelStar") String hotelStar, @Param("hotelDescription") String hotelDescription);

    /**
     * 按照工作人员ID获取酒店ID
     *
     * @param staffId 工作人员ID
     * @return HotelVO
     */
    HotelVO selectByStaffId(@Param("staffId") int staffId);

    /**
     * 根据酒店工作人员id获取所属酒店数量
     *
     * @param staffId 工作人员ID
     * @return int
     */
    int getStaffHotelNum(@Param("staffId") int staffId);
}