package com.example.hotel.bl.order;

import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

/**
 * @author chenyizong
 */
public interface OrderService {

    /**
     * 预订酒店
     *
     * @param orderVO 订单VO
     * @return ResponseVO
     */
    ResponseVO addOrder(OrderVO orderVO);

    /**
     * 获得所有订单信息
     *
     * @return 所有订单信息
     */
    List<Order> getAllOrders();

    /**
     * 获得指定用户的所有订单信息
     *
     * @param userId 用户ID
     * @return 指定用户的所有订单信息
     */
    List<Order> getUserOrders(int userId);

    /**
     * 撤销订单
     *
     * @param orderId 订单ID
     * @return ResponseVO
     */
    ResponseVO annulOrder(int orderId);

    /**
     * 恢复订单
     *
     * @param orderId 订单ID
     * @return ResponseVO
     */
    ResponseVO restoreOrder(int orderId);

    /**
     * 执行订单
     *
     * @param orderId 订单ID
     * @return ResponseVO
     */
    ResponseVO execOrder(int orderId);

    /**
     * 延迟入住，更新订单状态，恢复信用值
     *
     * @param orderId 订单ID
     * @return ResponseVO
     */
    ResponseVO recheck(int orderId);

    /**
     * 逾期，更新订单状态。扣除信用值
     *
     * @param orderId 订单ID
     * @return ResponseVO
     */
    ResponseVO overdue(int orderId);

    /**
     * 退房的订单状态更新
     *
     * @param orderId 订单ID
     * @return ResponseVO
     */
    ResponseVO checkout(int orderId);

    /**
     * 根据酒店工作人员的id来获取所属酒店的订单
     *
     * @param userId 用户ID
     * @return 酒店工作人员所属酒店订单
     */
    List<Order> getHotelOrdersByStaffId(int userId);

    /**
     * 撤销异常订单
     *
     * @param orderId 订单ID
     * @param ratio   恢复信用值的比例，0~1
     * @return ResponseVO
     */
    ResponseVO cancelAbnormalOrder(int orderId, double ratio);
}