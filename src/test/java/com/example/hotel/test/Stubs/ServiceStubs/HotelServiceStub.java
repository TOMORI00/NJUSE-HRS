package com.example.hotel.test.Stubs.ServiceStubs;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.Hotel;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.User;
import com.example.hotel.test.Stubs.DataStubs.HotelMapperStub;
import com.example.hotel.test.Stubs.DataStubs.OrderMapperStub;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.RoomVO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Daiqj
 */

public class HotelServiceStub implements HotelService {

    private final HotelMapper hotelMapper = new HotelMapperStub();

    private final OrderMapper orderMapper = new OrderMapperStub();

    private final AccountService accountService = new AccountServiceStub();

    private final RoomService roomService = new RoomServiceStub();

    @Override
    public ResponseVO addHotel(HotelVO hotelVO) {
        User manager = accountService.getUserInfo(hotelVO.getManagerId());
        if (manager == null || !manager.getUserType().equals(UserType.Staff)) {
            return ResponseVO.buildFailure("酒店工作人员不存在或者无权限！创建酒店失败！");
        }
        int staffHotelNum = hotelMapper.getStaffHotelNum(hotelVO.getManagerId());
        if (staffHotelNum > 0) {
            return ResponseVO.buildFailure("酒店工作人员已有所属酒店！");
        }
        Hotel hotel = new Hotel();
        hotel.setDescription(hotelVO.getDescription());
        hotel.setAddress(hotelVO.getAddress());
        hotel.setHotelName(hotelVO.getName());
        hotel.setPhoneNum(hotelVO.getPhoneNum());
        hotel.setManagerId(hotelVO.getManagerId());
        hotel.setRate(hotelVO.getRate());
        hotel.setBizRegion(BizRegion.valueOf(hotelVO.getBizRegion()));
        hotel.setHotelStar(HotelStar.valueOf(hotelVO.getHotelStar()));
        hotelMapper.insertHotel(hotel);
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO updateRoomInfo(Integer hotelId, String roomType, Integer rooms) {
        if (hotelId <= 0 || rooms <= 0) {
            return ResponseVO.buildFailure("无效输入");
        } else {
            roomService.updateRoomInfo(hotelId, roomType, rooms);
            return ResponseVO.buildSuccess(true);
        }
    }

    @Override
    public int getRoomCurNum(Integer hotelId, String roomType) {
        return roomService.getRoomCurNum(hotelId, roomType);
    }

    @Override
    public List<HotelVO> retrieveHotels() {

        return hotelMapper.selectAllHotel();
    }

    @Override
    public HotelVO retrieveHotelDetails(Integer hotelId) {
        HotelVO hotelVO = hotelMapper.selectById(hotelId);
        List<HotelRoom> rooms = roomService.retrieveHotelRoomInfo(hotelId);
        try {
            List<RoomVO> roomVOList = rooms.stream().map(r -> {
                RoomVO roomVO = new RoomVO();
                roomVO.setId(r.getId());
                roomVO.setPrice(r.getPrice());
                roomVO.setRoomType(r.getRoomType().toString());
                roomVO.setCurNum(r.getCurNum());
                roomVO.setTotal(r.getTotal());
                return roomVO;
            }).collect(Collectors.toList());
            hotelVO.setRooms(roomVOList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hotelVO;
    }

    @Override
    public ResponseVO changeHotelRate(Integer orderId, Integer hotelId, Double rate) {
        int botBorder = 0;
        int topBorder = 5;
        if (orderId <= 0 || hotelId <= 0 || rate < botBorder || rate > topBorder) {
            return ResponseVO.buildFailure("无效输入");
        } else {
            HotelVO hotelVO = hotelMapper.selectById(hotelId);
            Double originRate = hotelVO.getRate();
            int hotelFinishOrderNum = orderMapper.getHotelFinishOrderNum(hotelId);
            Double afterRate = ((originRate * hotelFinishOrderNum) + rate) / (hotelFinishOrderNum + 1);
            hotelMapper.changeHotelRate(hotelId, afterRate);
            orderMapper.finishOrder(orderId);
            return ResponseVO.buildSuccess(true);
        }
    }

    @Override
    public ResponseVO updateHotelInfo(int hotelId, String hotelAddress, String bizRegion, String hotelStar, String hotelDescription) {
        if (hotelId <= 0) {
            return ResponseVO.buildFailure("输入无效");
        } else {
            hotelMapper.updateHotelInfo(hotelId, hotelAddress, bizRegion, hotelStar, hotelDescription);
            return ResponseVO.buildSuccess(true);
        }
    }

}
