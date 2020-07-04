package com.example.hotel.test.Unit.data;

import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.po.Order;
import com.example.hotel.test.BasicTest;
import com.example.hotel.vo.OrderVO;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


public class OrderMapperTest extends BasicTest {
    @Autowired
    OrderMapper orderMapper;

    @Test
    @Transactional
    @Rollback
    public void addOrderTest1() {
        OrderVO orderVO = new OrderVO();
        orderVO.setHotelId(1);
        orderVO.setHotelName("汉庭酒店");
        orderVO.setCheckInDate("2020-06-18");
        orderVO.setCheckOutDate("2020-06-20");
        orderVO.setPrice(300.0);
        orderVO.setRoomNum(1);
        orderVO.setHaveChild(true);
        orderVO.setUserId(5);
        orderVO.setRoomType("BigBed");
        orderVO.setPeopleNum(3);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String curdate = sf.format(date);
        orderVO.setCreateDate(curdate);
        orderVO.setOrderState("已预订");
        orderVO.setClientName("测试添加订单用户名");
        orderVO.setPhoneNumber("12345678910");
        Order order = new Order();
        BeanUtils.copyProperties(orderVO, order);
        try {
            orderMapper.addOrder(order);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void getAllOrdersTest1() {
        try {
            List<Order> orderList = orderMapper.getAllOrders();
            assertNotNull(orderList);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void getUserOrdersTest1() {
        List<Order> orderList = orderMapper.getUserOrders(5);
        for (Order order : orderList) {
            if (order.getUserId() != 5) {
                fail();
            }
        }
    }

    @Test
    @Transactional
    @Rollback
    public void annulOrderTest1() {
        orderMapper.annulOrder(14);
        Order order = orderMapper.getOrderById(14);
        assertEquals("已撤销", order.getOrderState());
    }

    @Test
    @Transactional
    @Rollback
    public void execOrderTest1() {
        orderMapper.execOrder(14);
        Order order = orderMapper.getOrderById(14);
        assertEquals("已入住", order.getOrderState());
    }

    @Test
    @Transactional
    @Rollback
    public void restoreOrderTest1() {
        orderMapper.restoreOrder(14);
        Order order = orderMapper.getOrderById(14);
        assertEquals("已入住", order.getOrderState());
    }

    @Test
    public void getHotelOrdersTest1() {
        List<Order> orderList = orderMapper.getHotelOrders(3);
        for (Order order : orderList) {
            if (order.getHotelId() != 3) {
                fail();
            }
        }
    }

    @Test
    @Transactional
    @Rollback
    public void checkoutOrderTest1() {
        orderMapper.checkoutOrder(14);
        Order order = orderMapper.getOrderById(14);
        assertEquals("待评价", order.getOrderState());
    }


}
