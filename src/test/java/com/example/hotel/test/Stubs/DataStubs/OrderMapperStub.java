package com.example.hotel.test.Stubs.DataStubs;

import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.po.Order;

import java.util.LinkedList;
import java.util.List;

public class OrderMapperStub implements OrderMapper {
    @Override
    public int addOrder(Order order) {
        return 0;
    }

    @Override
    public List<Order> getAllOrders() {
        LinkedList<Order> orders = new LinkedList<>();
        Order order = new Order();
        order.setId(100);
        orders.add(order);
        return orders;

    }

    @Override
    public List<Order> getUserOrders(int userid) {
        return null;
    }

    @Override
    public int annulOrder(int orderid) {
        return 0;
    }

    @Override
    public Order getOrderById(int orderid) {
        return null;
    }

    @Override
    public int execOrder(int orderid) {
        return 0;
    }

    @Override
    public void restoreOrder(int orderid) {

    }

    @Override
    public List<Order> getHotelOrders(int hotelid) {
        return null;
    }

    @Override
    public int checkoutOrder(int orderid) {
        return 0;
    }

    @Override
    public int recheck(int orderid) {
        return 0;
    }

    @Override
    public int overdue(int orderid) {
        return 0;
    }

    @Override
    public int cancelAbnormalOrder(int orderid) {
        return 0;
    }

    @Override
    public void finishOrder(int orderid) {

    }

    @Override
    public int getHotelFinishOrderNum(int hotelid) {
        return 0;
    }
}
