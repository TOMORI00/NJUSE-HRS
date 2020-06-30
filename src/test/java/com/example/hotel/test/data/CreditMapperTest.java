package com.example.hotel.test.data;

import com.example.hotel.data.credit.CreditMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.CreditRecord;
import com.example.hotel.test.BasicTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CreditMapperTest extends BasicTest {
    @Autowired
    CreditMapper creditMapper;
    @Autowired
    AccountMapper accountMapper;

    @Test
    @Transactional
    @Rollback
    public void insertCreditRecordTest1() {
        CreditRecord creditRecord = new CreditRecord();
        creditRecord.setUserId(8);
        creditRecord.setOrderId(1);
        creditRecord.setChangeReason("执行订单");
        creditRecord.setRecordCredit(100);
        creditRecord.setRecordTime(new Date(System.currentTimeMillis()));
        try {
            creditMapper.insertCreditRecord(creditRecord);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void selectCreditRecordByIdTest1() {
        List<CreditRecord> creditRecordList = creditMapper.selectCreditRecordById(5);
        for (CreditRecord creditRecord : creditRecordList) {
            if (creditRecord.getUserId() != 5) {
                fail();
            }
        }
    }

    @Test
    @Transactional
    @Rollback
    //加信用
    public void updateUserCreditTest1() {
        double currCredit = accountMapper.getAccountById(5).getCredit();
        creditMapper.updateUserCredit(50, 5);
        double afterCredit = accountMapper.getAccountById(5).getCredit();
        assertEquals(afterCredit, currCredit + 50, 0.0);
    }

    @Test
    @Transactional
    @Rollback
    //减信用
    public void updateUserCreditTest2() {
        double currCredit = accountMapper.getAccountById(5).getCredit();
        creditMapper.updateUserCredit(-50, 5);
        double afterCredit = accountMapper.getAccountById(5).getCredit();
        assertEquals(afterCredit, currCredit - 50, 0.0);
    }


}
