package com.example.hotel.blimpl.user;

import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.credit.CreditMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.CreditRecord;
import com.example.hotel.po.User;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Daiqj
 */
@Service
public class AccountServiceImpl implements AccountService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String UPDATE_ERROR = "修改失败";
    private final static String RECHARGE_ERROR = "充值失败";
    private final static String CHANGE_REASON_RECHARGE = "信用充值";

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private CreditMapper creditMapper;

    @Override
    public ResponseVO registerAccount(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        try {
            accountMapper.createNewAccount(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public User login(UserForm userForm) {
        User user = accountMapper.getAccountByName(userForm.getEmail());
        if (null == user || !user.getPassword().equals(userForm.getPassword())) {
            return null;
        }
        return user;
    }

    @Override
    public User getUserInfo(int id) {
        return accountMapper.getAccountById(id);
    }

    @Override
    public ResponseVO updateUserInfo(int id, String password, String username, String phonenumber) {
        if (id < 0) {
            return ResponseVO.buildFailure("id错误");
        }
        try {
            accountMapper.updateAccount(id, password, username, phonenumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<CreditRecord> getCreditRecord(Integer userId) {
        List<CreditRecord> creditRecordList = creditMapper.selectCreditRecordById(userId);
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (CreditRecord creditRecord : creditRecordList) {
            Date recordTime = creditRecord.getRecordTime();
            creditRecord.setRecordTimeStr(ft.format(recordTime));
        }
        return creditRecordList;
    }

    @Override
    public ResponseVO rechargeUserCredit(double changeCredit, int userId) {
        try {
            //更新信用记录
            CreditRecord creditRecord = new CreditRecord();
            creditRecord.setUserId(userId);
            creditRecord.setChangeReason(CHANGE_REASON_RECHARGE);
            creditRecord.setOrderId(0);
            creditRecord.setRecordCredit(changeCredit);
            creditRecord.setRecordTime(new Date(System.currentTimeMillis()));
            User user = accountMapper.getAccountById(userId);
            double currCredit = user.getCredit();
            creditRecord.setCreditResult(currCredit + changeCredit);
            creditMapper.insertCreditRecord(creditRecord);
            //修改信用值
            creditMapper.updateUserCredit(changeCredit, userId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(RECHARGE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }
}