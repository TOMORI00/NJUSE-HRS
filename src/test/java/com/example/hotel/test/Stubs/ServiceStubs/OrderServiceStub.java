package com.example.hotel.test.Stubs.ServiceStubs;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.credit.CreditMapper;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.CreditRecord;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.test.Stubs.DataStubs.AccountMapperStub;
import com.example.hotel.test.Stubs.DataStubs.CreditMapperStub;
import com.example.hotel.test.Stubs.DataStubs.HotelMapperStub;
import com.example.hotel.test.Stubs.DataStubs.OrderMapperStub;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.BeanUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderServiceStub implements OrderService {
    private final static String RESERVE_ERROR = "预订失败";
    private final static String ROOMNUM_LACK = "预订房间数量剩余不足";
    private final static String ANNUL_ERROR = "撤销失败";
    private final static String CHANGE_REASON_TIMEOUT_ANNUL = "超时撤销订单";
    private final static String CHANGE_REASON_RESTORE = "恢复订单";
    private final static String CHANGE_REASON_EXEC = "执行订单";
    private final static String CHANGE_REASON_RECHECK = "延迟入住";
    private final static String CHANGE_REASON_OVERDUE = "逾期";
    private final static String CHANGE_REASON_CANCEL_ABNORMAL = "撤销异常";
    /**
     * 在入住日期0点前BEFORE_HOUR个小时前撤销订单不扣除信用值
     */
    private final static int BEFORE_HOUR = 6;

    OrderMapper orderMapper = new OrderMapperStub();

    CreditMapper creditMapper = new CreditMapperStub();

    AccountMapper accountMapper = new AccountMapperStub();

    HotelMapper hotelMapper = new HotelMapperStub();

    HotelService hotelService = new HotelServiceStub();

    AccountService accountService = new AccountServiceStub();

    RoomService roomService = new RoomServiceStub();

    @Override
    public ResponseVO addOrder(OrderVO orderVO) {
        int reserveRoomNum = orderVO.getRoomNum();
        int curNum = hotelService.getRoomCurNum(orderVO.getHotelId(), orderVO.getRoomType());
        if (reserveRoomNum > curNum) {
            return ResponseVO.buildFailure(ROOMNUM_LACK);
        }
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String curDate = sf.format(date);
            orderVO.setCreateDate(curDate);
            orderVO.setOrderState("已预订");
            User user = accountService.getUserInfo(orderVO.getUserId());
            orderVO.setClientName(user.getUserName());
            orderVO.setPhoneNumber(user.getPhoneNumber());
            Order order = new Order();
            BeanUtils.copyProperties(orderVO, order);
            orderMapper.addOrder(order);
            hotelService.updateRoomInfo(orderVO.getHotelId(), orderVO.getRoomType(), orderVO.getRoomNum());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(RESERVE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    @Override
    public List<Order> getUserOrders(int userId) {
        return orderMapper.getUserOrders(userId);
    }

    @Override
    public ResponseVO annulOrder(int orderId) {
        //取消订单逻辑的具体实现（注意可能有和别的业务类之间的交互）
        orderMapper.annulOrder(orderId);
        Order order = orderMapper.getOrderById(orderId);
        try {
            //关于房间数恢复的逻辑
            Integer roomNum = order.getRoomNum();
            Integer hotelId = order.getHotelId();
            String roomType = order.getRoomType();
            roomService.restoreRoom(hotelId, roomType, roomNum);
            //关于信用的逻辑
            String checkInDateStr = order.getCheckInDate();
            SimpleDateFormat ymdDateft = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //计算最晚执行时间
            Date checkInDate = ymdDateft.parse(checkInDateStr);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(checkInDate);
            rightNow.add(Calendar.HOUR, -BEFORE_HOUR);
            Date latestTime = rightNow.getTime();
            //获取当前时间
            Date curTime = new Date(System.currentTimeMillis());
            System.out.println("最晚执行时间：" + ft.format(latestTime));
            System.out.println("当前时间：" + ft.format(curTime));
            //compareTo()方法的返回值，date1小于date2返回-1，date1大于date2返回1，相等返回0
            int compareTo = curTime.compareTo(latestTime);
            System.out.println(compareTo);
            //当前时间比最晚执行时间晚，则扣信用
            if (compareTo >= 0) {
                //改变信用的算法
                double changeCredit = order.getPrice() / 2;
                changeCredit = -changeCredit;
                //存储信用变更记录
                CreditRecord creditRecord = new CreditRecord();
                creditRecord.setUserId(order.getUserId());
                creditRecord.setOrderId(orderId);
                creditRecord.setRecordTime(curTime);
                creditRecord.setRecordCredit(changeCredit);
                creditRecord.setChangeReason(CHANGE_REASON_TIMEOUT_ANNUL);
                User user = accountMapper.getAccountById(order.getUserId());
                double currCredit = user.getCredit();
                creditRecord.setCreditResult(currCredit + changeCredit);
                creditMapper.insertCreditRecord(creditRecord);
                //改变用户信用值
                creditMapper.updateUserCredit(changeCredit, order.getUserId());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ANNUL_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO restoreOrder(int orderId) {
        //更新订单状态
        orderMapper.restoreOrder(orderId);
        try {
            //恢复信用
            Order order = orderMapper.getOrderById(orderId);
            Integer userId = order.getUserId();
            creditMapper.updateUserCredit(order.getPrice() / 2, userId);
            //信用变更记录
            CreditRecord creditRecord = new CreditRecord();
            creditRecord.setUserId(userId);
            creditRecord.setChangeReason(CHANGE_REASON_RESTORE);
            creditRecord.setOrderId(orderId);
            creditRecord.setRecordCredit(order.getPrice() / 2);
            creditRecord.setRecordTime(new Date(System.currentTimeMillis()));
            User user = accountMapper.getAccountById(order.getUserId());
            double currCredit = user.getCredit();
            creditRecord.setCreditResult(currCredit);
            creditMapper.insertCreditRecord(creditRecord);
        } catch (Exception e) {
            return ResponseVO.buildFailure("恢复失败");
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO execOrder(int orderId) {
        //更新订单状态
        orderMapper.execOrder(orderId);
        try {
            //增加信用
            Order order = orderMapper.getOrderById(orderId);
            Integer userId = order.getUserId();
            creditMapper.updateUserCredit(order.getPrice(), userId);
            //信用变更记录
            CreditRecord creditRecord = new CreditRecord();
            creditRecord.setUserId(userId);
            creditRecord.setChangeReason(CHANGE_REASON_EXEC);
            creditRecord.setOrderId(orderId);
            creditRecord.setRecordCredit(order.getPrice());
            creditRecord.setRecordTime(new Date(System.currentTimeMillis()));
            User user = accountMapper.getAccountById(order.getUserId());
            double currCredit = user.getCredit();
            creditRecord.setCreditResult(currCredit);
            creditMapper.insertCreditRecord(creditRecord);
        } catch (Exception e) {
            return ResponseVO.buildFailure("执行失败");
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO recheck(int orderid) {
        Order order = orderMapper.getOrderById(orderid);
        try {
            //更新订单状态
            orderMapper.recheck(orderid);

            //扣除房间数
            Integer roomNum = order.getRoomNum();
            Integer hotelId = order.getHotelId();
            String roomType = order.getRoomType();
            roomService.updateRoomInfo(hotelId, roomType, roomNum);

            //恢复信用,全额恢复
            Integer userId = order.getUserId();
            creditMapper.updateUserCredit(order.getPrice(), userId);

            //信用变更记录
            CreditRecord creditRecord = new CreditRecord();
            creditRecord.setUserId(userId);
            creditRecord.setChangeReason(CHANGE_REASON_RECHECK);
            creditRecord.setOrderId(orderid);
            creditRecord.setRecordCredit(order.getPrice());
            creditRecord.setRecordTime(new Date(System.currentTimeMillis()));
            User user = accountMapper.getAccountById(order.getUserId());
            double currCredit = user.getCredit();
            creditRecord.setCreditResult(currCredit);
            creditMapper.insertCreditRecord(creditRecord);
        } catch (Exception e) {
            return ResponseVO.buildFailure("失败");
        }

        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO overdue(int orderId) {
        Order order = orderMapper.getOrderById(orderId);
        try {

            //更新订单状态
            orderMapper.overdue(orderId);

            //恢复房间数
            Integer roomNum = order.getRoomNum();
            Integer hotelId = order.getHotelId();
            String roomType = order.getRoomType();
            roomService.restoreRoom(hotelId, roomType, roomNum);

            //扣除信用
            Integer userId = order.getUserId();
            creditMapper.updateUserCredit(-order.getPrice(), userId);

            //信用变更记录
            CreditRecord creditRecord = new CreditRecord();
            creditRecord.setUserId(userId);
            creditRecord.setChangeReason(CHANGE_REASON_OVERDUE);
            creditRecord.setOrderId(orderId);
            creditRecord.setRecordCredit(-order.getPrice());
            creditRecord.setRecordTime(new Date(System.currentTimeMillis()));
            User user = accountMapper.getAccountById(order.getUserId());
            double currCredit = user.getCredit();
            System.out.println(currCredit);
            creditRecord.setCreditResult(currCredit);
            creditMapper.insertCreditRecord(creditRecord);
        } catch (Exception e) {
            return ResponseVO.buildFailure("失败");
        }

        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO checkout(int orderid) {
        Order order = orderMapper.getOrderById(orderid);
        try {
            int hotelId = order.getHotelId();
            int roomNum = order.getRoomNum();
            String roomType = order.getRoomType();

            //修改剩余客房,把退的房加回
            roomService.restoreRoom(hotelId, roomType, roomNum);

            //修改订单状态
            orderMapper.checkoutOrder(orderid);
        } catch (Exception e) {
            return ResponseVO.buildFailure("失败");
        }
        return ResponseVO.buildSuccess(true);
    }


    @Override
    public List<Order> getHotelOrdersByStaffId(int userId) {
        HotelVO hotelVO = hotelMapper.selectByStaffId(userId);
        if (hotelVO == null) {
            System.out.println("该酒店工作人员不属于任何酒店！");
            //返回空列表
            return orderMapper.getHotelOrders(-1);
        }
        int hotelId = hotelVO.getId();
        return orderMapper.getHotelOrders(hotelId);
    }

    @Override
    public ResponseVO cancelAbnormalOrder(int orderId, double ratio) {
        //更新订单状态
        orderMapper.cancelAbnormalOrder(orderId);
        try {

            //根据恢复比例ratio，恢复信用
            Order order = orderMapper.getOrderById(orderId);
            Integer userId = order.getUserId();
            creditMapper.updateUserCredit(order.getPrice() * ratio, userId);

            //信用变更记录
            CreditRecord creditRecord = new CreditRecord();
            creditRecord.setUserId(userId);
            creditRecord.setChangeReason(CHANGE_REASON_CANCEL_ABNORMAL);
            creditRecord.setOrderId(orderId);
            creditRecord.setRecordCredit(order.getPrice() * ratio);
            creditRecord.setRecordTime(new Date(System.currentTimeMillis()));
            User user = accountMapper.getAccountById(order.getUserId());
            double currCredit = user.getCredit();
            System.out.println(currCredit);
            creditRecord.setCreditResult(currCredit);
            creditMapper.insertCreditRecord(creditRecord);
        } catch (Exception e) {
            return ResponseVO.buildFailure("失败");
        }

        return ResponseVO.buildSuccess(true);
    }
}