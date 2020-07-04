package com.example.hotel.test.Stubs.DataStubs;

import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.po.Hotel;
import com.example.hotel.vo.HotelVO;

import java.util.LinkedList;
import java.util.List;

public class HotelMapperStub implements HotelMapper {
    @Override
    public int insertHotel(Hotel hotel) {
        return 0;
    }

    @Override
    public List<HotelVO> selectAllHotel() {
        LinkedList<HotelVO> hotelVOS = new LinkedList<>();
        HotelVO hotelVO = new HotelVO();
        hotelVO.setManagerId(1);
        hotelVOS.add(hotelVO);
        return hotelVOS;
    }

    @Override
    public HotelVO selectById(Integer id) {
        HotelVO hotelVO = new HotelVO();
        hotelVO.setManagerId(1);
        return hotelVO;
    }

    @Override
    public void changeHotelRate(Integer hotelId, Double rate) {

    }

    @Override
    public int updateHotelInfo(Integer hotelid, String hotelAddress, String bizRegion, String hotelStar, String hotelDescription) {
        return 0;
    }

    @Override
    public HotelVO selectByStaffId(int staffId) {
        HotelVO hotelVO = new HotelVO();
        hotelVO.setManagerId(1);
        return hotelVO;
    }

    @Override
    public int getStaffHotelNum(int staffId) {
        return 0;
    }
}
