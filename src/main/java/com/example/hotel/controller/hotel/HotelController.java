package com.example.hotel.controller.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Daiqj
 */
@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/addHotel")
    public ResponseVO createHotel(@RequestBody HotelVO hotelVO) {
        return hotelService.addHotel(hotelVO);
    }

    @GetMapping("/all")
    public ResponseVO retrieveAllHotels() {
        return ResponseVO.buildSuccess(hotelService.retrieveHotels());
    }

    @PostMapping("/roomInfo")
    public ResponseVO addRoomInfo(@RequestBody HotelRoom hotelRoom) {
        return roomService.insertRoomInfo(hotelRoom);
    }

    @GetMapping("/{hotelId}/detail")
    public ResponseVO retrieveHotelDetail(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(hotelService.retrieveHotelDetails(hotelId));
    }

    @PostMapping("/changeHotelRate")
    public ResponseVO changeHotelRate(@RequestBody RateVO rateVO) {
        hotelService.changeHotelRate(rateVO.getOrderId(), rateVO.getHotelId(), rateVO.getRate());
        return ResponseVO.buildSuccess(true);
    }

    @PostMapping("/{id}/hotelInfo/update")
    public ResponseVO updateInfo(@RequestBody HotelInfoVO hotelInfoVO, @PathVariable int id) {
        String hotelLocation = hotelInfoVO.getHotelLocation();
        String bizRegion = hotelInfoVO.getBizRegion();
        String hotelStar = hotelInfoVO.getHotelStar();
        String description = hotelInfoVO.getDescription();
        hotelService.updateHotelInfo(id, hotelLocation, bizRegion, hotelStar, description);
        return ResponseVO.buildSuccess(true);
    }
}