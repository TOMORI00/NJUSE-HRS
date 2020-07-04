package com.example.hotel.test.Stubs.DataStubs;

import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.po.HotelRoom;

import java.util.LinkedList;
import java.util.List;

public class RoomMapperStub implements RoomMapper {
    @Override
    public int updateRoomInfo(Integer hotelId, String roomType, Integer curNum) {
        return 0;
    }

    @Override
    public int insertRoom(HotelRoom hotelRoom) {
        return 0;
    }

    @Override
    public List<HotelRoom> selectRoomsByHotelId(Integer hotelId) {
        LinkedList<HotelRoom> hotelRooms = new LinkedList<>();
        HotelRoom hotelRoom = new HotelRoom();
        hotelRoom.setTotal(12);
        hotelRooms.add(hotelRoom);
        return hotelRooms;
    }

    @Override
    public int getRoomCurNum(Integer hotelId, String roomType) {
        return 0;
    }

    @Override
    public void restoreRoom(Integer hotelId, String roomType, Integer curNum) {

    }
}
