package com.example.hotel.test.Unit.service;

import com.example.hotel.bl.order.OrderService;
import com.example.hotel.test.BasicTest;
import com.example.hotel.test.Stubs.ServiceStubs.OrderServiceStub;
import com.example.hotel.vo.OrderVO;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertFalse;

public class OrderServiceTest extends BasicTest {

    OrderService orderService = new OrderServiceStub();

    @Test
    @Transactional
    @Rollback
    public void addOrder() {
        //正常
        OrderVO orderVO_Normal = new OrderVO();
        orderVO_Normal.setId(1);
        orderVO_Normal.setUserId(4);
        orderVO_Normal.setHotelId(1);
        orderVO_Normal.setHotelName("test_name");
        orderVO_Normal.setCheckInDate("2020-05-20");
        orderVO_Normal.setCheckOutDate("2020-05-21");
        orderVO_Normal.setRoomType("BigBed");
        orderVO_Normal.setRoomNum(3);
        orderVO_Normal.setPeopleNum(3);
        orderVO_Normal.setHaveChild(true);
        orderVO_Normal.setCreateDate("2020-05-11");
        orderVO_Normal.setPrice(1000.0);
        orderVO_Normal.setClientName("test_client");
        orderVO_Normal.setPhoneNumber("1111");
        orderVO_Normal.setOrderState("已预定");
        System.out.println(orderVO_Normal.toString());
        assertFalse(orderService.addOrder(orderVO_Normal).getSuccess());

        //异常
        OrderVO orderVO_Error = new OrderVO();
        orderVO_Error.setId(-1);
        orderVO_Error.setUserId(-1);
        orderVO_Error.setHotelId(1);
        orderVO_Error.setHotelName("test_name");
        orderVO_Error.setCheckInDate("2020-05-20");
        orderVO_Error.setCheckOutDate("2020-05-21");
        orderVO_Error.setRoomType("BigBed");
        orderVO_Error.setRoomNum(3);
        orderVO_Error.setPeopleNum(3);
        orderVO_Error.setHaveChild(true);
        orderVO_Error.setCreateDate("2020-05-11");
        orderVO_Error.setPrice(1000.0);
        orderVO_Error.setClientName("test_client");
        orderVO_Error.setPhoneNumber("1111");
        orderVO_Error.setOrderState("已预定");
        System.out.println(orderVO_Error.toString());
        assertFalse(orderService.addOrder(orderVO_Error).getSuccess());
    }

    @Test
    public void getAllOrders() {
        orderService.getAllOrders().forEach(order -> System.out.println(order.toString()));
    }


    @Test
    @Transactional
    @Rollback
    public void annulOrder() {
        //正常
        int orderId_Normal = 14;
        assertFalse(orderService.annulOrder(orderId_Normal).getSuccess());


        //异常
        int orderId_Error = -1;
        assertFalse(orderService.annulOrder(orderId_Error).getSuccess());

    }

    @Test
    @Transactional
    @Rollback
    public void restoreOrder() {
        //正常
        int orderId_Normal = 14;
        assertFalse(orderService.restoreOrder(orderId_Normal).getSuccess());

        //异常
        int orderId_Error = -1;
        assertFalse(orderService.restoreOrder(orderId_Error).getSuccess());

    }

    @Test
    @Transactional
    @Rollback
    public void execOrder() {
        //正常
        int orderId_Normal = 14;
        assertFalse(orderService.execOrder(orderId_Normal).getSuccess());

        //异常
        int orderId_Error = -1;
        assertFalse(orderService.execOrder(orderId_Error).getSuccess());
    }

    @Test
    @Transactional
    @Rollback
    public void recheck() {
        //正常
        int orderId_Normal = 14;
        assertFalse(orderService.recheck(orderId_Normal).getSuccess());

        //异常
        int orderId_Error = -1;
        assertFalse(orderService.recheck(orderId_Error).getSuccess());

    }

    @Test
    @Transactional
    @Rollback
    public void overdue() {
        //正常
        int orderId_Normal = 14;
        assertFalse(orderService.overdue(orderId_Normal).getSuccess());

        //异常
        int orderId_Error = -1;
        assertFalse(orderService.overdue(orderId_Error).getSuccess());


    }

    @Test
    public void checkout() {
        //正常
        int orderId_Normal = 14;
        assertFalse(orderService.checkout(orderId_Normal).getSuccess());

        //异常
        int orderId_Error = -1;
        assertFalse(orderService.checkout(orderId_Error).getSuccess());

    }


    @Test
    public void cancelAbnormalOrder() {
        //正常
        int orderId_Normal = 14;
        assertFalse(orderService.cancelAbnormalOrder(orderId_Normal, 0.1).getSuccess());

        //异常
        int orderId_Error = -1;
        assertFalse(orderService.cancelAbnormalOrder(orderId_Error, 0.1).getSuccess());

    }
}