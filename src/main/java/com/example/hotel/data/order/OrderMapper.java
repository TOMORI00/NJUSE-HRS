package com.example.hotel.data.order;

import com.example.hotel.po.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenyizong
 */
@Mapper
@Repository
public interface OrderMapper {

    /**
     * 添加订单
     *
     * @param order 酒店id
     * @return int
     * @author ljl
     */
    int addOrder(Order order);

    /**
     * 获取所有订单
     *
     * @return 所有订单
     * @author ljl
     */
    List<Order> getAllOrders();

    /**
     * 获取某个用户的所有订单
     *
     * @param userid 用户ID
     * @return 某个用户的所有订单
     * @author ljl
     */
    List<Order> getUserOrders(@Param("userid") int userid);

    /**
     * 撤销订单
     *
     * @param orderid 订单id
     * @return int
     * @author ljl
     */
    int annulOrder(@Param("orderid") int orderid);

    /**
     * 按照ID获取订单
     *
     * @param orderid 订单id
     * @return Order
     * @author ljl
     */
    Order getOrderById(@Param("orderid") int orderid);

    /**
     * 执行订单
     *
     * @param orderid 订单id
     * @return int
     * @author ljl
     */
    int execOrder(@Param("orderid") int orderid);

    /**
     * 恢复订单
     *
     * @param orderid 订单id
     * @author ljl
     */
    void restoreOrder(@Param("orderid") int orderid);

    /**
     * 获取酒店所有订单
     *
     * @param hotelid 酒店id
     * @return 酒店所有订单
     * @author ljl
     */
    List<Order> getHotelOrders(@Param("hotelid") int hotelid);

    /**
     * 退房
     *
     * @param orderid 酒店id
     * @return int
     * @author ljl
     */
    int checkoutOrder(@Param("orderid") int orderid);

    /**
     * 延期入住
     *
     * @param orderid 订单id
     * @return int
     * @author ljl
     */
    int recheck(@Param("orderid") int orderid);

    /**
     * 逾期
     *
     * @param orderid 订单id
     * @return int
     * @author ljl
     */
    int overdue(@Param("orderid") int orderid);

    /**
     * 撤销异常
     *
     * @param orderid 订单id
     * @return int
     * @author ljl
     */
    int cancelAbnormalOrder(@Param("orderid") int orderid);

    /**
     * 完成订单
     *
     * @param orderid 订单id
     * @author ljl
     */
    void finishOrder(@Param("orderid") int orderid);

    /**
     * 获取某个酒店的已完成订单数量
     *
     * @param hotelid 酒店id
     * @return int
     * @author ljl
     */
    int getHotelFinishOrderNum(@Param("hotelid") int hotelid);
}