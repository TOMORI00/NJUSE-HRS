package com.example.hotel.controller.order;

import com.example.hotel.bl.order.OrderService;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chen yi zong
 */
@RestController()
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseVO reserveHotel(@RequestBody OrderVO orderVO) {
        return orderService.addOrder(orderVO);
    }

    @GetMapping("/getAllOrders")
    public ResponseVO retrieveAllOrders() {
        return ResponseVO.buildSuccess(orderService.getAllOrders());
    }

    @GetMapping("/{userId}/getUserOrders")
    public ResponseVO retrieveUserOrders(@PathVariable int userId) {
        return ResponseVO.buildSuccess(orderService.getUserOrders(userId));
    }

    @GetMapping("/{orderId}/annulOrder")
    public ResponseVO annulOrder(@PathVariable int orderId) {
        return orderService.annulOrder(orderId);
    }

    @GetMapping("/{orderId}/restoreOrder")
    public ResponseVO restoreOrder(@PathVariable int orderId) {
        return orderService.restoreOrder(orderId);
    }

    @GetMapping("/{orderId}/checkIn")
    public ResponseVO execOrder(@PathVariable int orderId) {
        return orderService.execOrder(orderId);
    }

    @GetMapping("/{orderId}/overdue")
    public ResponseVO overdue(@PathVariable int orderId) {
        return orderService.overdue(orderId);
    }

    @GetMapping("/{orderId}/recheck")
    public ResponseVO recheck(@PathVariable int orderId) {
        return orderService.recheck(orderId);
    }

    @GetMapping("/{orderId}/checkout")
    public ResponseVO checkout(@PathVariable int orderId) {
        return orderService.checkout(orderId);
    }

    @GetMapping("/{userId}/getHotelOrdersByStaffId")
    public ResponseVO getHotelOrdersByStaffId(@PathVariable int userId) {
        return ResponseVO.buildSuccess(orderService.getHotelOrdersByStaffId(userId));
    }

    @PostMapping("/cancelAbnormalOrder")
    public ResponseVO cancelAbnormalOrder(@RequestBody CancelAbnormalOrderVO cancelAbnormalOrderVO) {
        return orderService.cancelAbnormalOrder(cancelAbnormalOrderVO.getOrderId(), cancelAbnormalOrderVO.getRatio() * 0.01);
    }
}