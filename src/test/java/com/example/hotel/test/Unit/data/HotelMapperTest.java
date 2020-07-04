package com.example.hotel.test.Unit.data;

import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.po.Hotel;
import com.example.hotel.test.BasicTest;
import com.example.hotel.vo.HotelVO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

public class HotelMapperTest extends BasicTest {
    @Autowired
    HotelMapper hotelMapper;

    @Test
    @Transactional
    @Rollback
    //添加酒店
    public void insertHotelTest1() {
        Hotel hotel = new Hotel();
        hotel.setDescription("测试添加酒店1介绍");
        hotel.setAddress("测试添加酒店1地址");
        hotel.setHotelName("测试添加酒店1名称");
        hotel.setPhoneNum("12345678910");
        hotel.setManagerId(6);
        hotel.setRate(4.8);
        hotel.setBizRegion(BizRegion.valueOf("XiDan"));
        hotel.setHotelStar(HotelStar.valueOf("Five"));
        try {
            hotelMapper.insertHotel(hotel);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void selectAllHotelTest1() {
        List<HotelVO> hotelList = hotelMapper.selectAllHotel();
        assertNotNull(hotelList);
    }

    @Test
    //正常查询酒店
    public void selectByIdTest1() {
        HotelVO hotelVO = hotelMapper.selectById(1);
        assertEquals("汉庭酒店", hotelVO.getName());
    }

    @Test
    //查询不存在的酒店
    public void selectByIdTest2() {
        HotelVO hotelVO = hotelMapper.selectById(-1);
        assertNull(hotelVO);
    }

    @Test
    @Transactional
    @Rollback
    public void changeHotelRateTest1() {
        double rate = 5.0;
        hotelMapper.changeHotelRate(1, rate);
        HotelVO hotelVO = hotelMapper.selectById(1);
        assertEquals(rate, hotelVO.getRate(), 0.0);
    }

    @Test
    @Transactional
    @Rollback
    public void updateHotelInfoTest1() {
        hotelMapper.updateHotelInfo(1, "测试更新地址", "测试更新商圈", "测试更新星级", "测试更新描述");
        HotelVO hotelVO = hotelMapper.selectById(1);
        assertEquals("测试更新地址", hotelVO.getAddress());
        assertEquals("测试更新商圈", hotelVO.getBizRegion());
        assertEquals("测试更新星级", hotelVO.getHotelStar());
        assertEquals("测试更新描述", hotelVO.getDescription());
    }

    @Test
    public void selectByStaffIdTest1() {
        int staffId = 7;
        HotelVO hotelVO = hotelMapper.selectByStaffId(staffId);
        assertEquals(3, (int) hotelVO.getId());
    }

    @Test
    public void getStaffHotelNumTest1() {
        int hotelNum = hotelMapper.getStaffHotelNum(7);
        assertEquals(1, hotelNum);
    }

    @Test
    public void getStaffHotelNumTest2() {
        int hotelNum = hotelMapper.getStaffHotelNum(-1);
        assertEquals(0, hotelNum);
    }


}
