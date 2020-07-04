package com.example.hotel.test.Stubs.ServiceStubs;

import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.test.Stubs.DataStubs.RoomMapperStub;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

public class RoomServiceStub implements RoomService {
    private static final String INSERT_ROOM_ERROR = "房型已存在！";

    private final RoomMapper roomMapper = new RoomMapperStub();

    @Override
    public List<HotelRoom> retrieveHotelRoomInfo(Integer hotelId) {
        return roomMapper.selectRoomsByHotelId(hotelId);
    }

    @Override
    public ResponseVO insertRoomInfo(HotelRoom hotelRoom) {
        if (hotelRoom.getId() < 0 || hotelRoom.getPrice() < 0 || hotelRoom.getHotelId() < 0 || hotelRoom.getCurNum() < 0) {
            return ResponseVO.buildFailure("无效输入");
        }
        try {
            roomMapper.insertRoom(hotelRoom);
        } catch (Exception e) {
            return ResponseVO.buildFailure(INSERT_ROOM_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO updateRoomInfo(Integer hotelId, String roomType, Integer rooms) {
        if (hotelId < 0 || rooms < 0) {
            return ResponseVO.buildFailure("无效输入");
        } else {
            roomMapper.updateRoomInfo(hotelId, roomType, rooms);
            return ResponseVO.buildSuccess(true);
        }
    }

    @Override
    public int getRoomCurNum(Integer hotelId, String roomType) {
        int result = -1;
        try {
            result = roomMapper.getRoomCurNum(hotelId, roomType);
        } catch (Exception e) {
            return -1;
        }
        return result;
    }

    @Override
    public ResponseVO restoreRoom(Integer hotelId, String roomType, Integer rooms) {
        if (hotelId < 0 || rooms < 0) {
            return ResponseVO.buildFailure("无效输入");
        } else {
            roomMapper.restoreRoom(hotelId, roomType, rooms);
            return ResponseVO.buildSuccess(true);
        }
    }
}