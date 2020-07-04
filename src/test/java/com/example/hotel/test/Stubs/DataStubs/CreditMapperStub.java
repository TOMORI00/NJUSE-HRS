package com.example.hotel.test.Stubs.DataStubs;

import com.example.hotel.data.credit.CreditMapper;
import com.example.hotel.po.CreditRecord;

import java.util.LinkedList;
import java.util.List;

public class CreditMapperStub implements CreditMapper {
    @Override
    public int insertCreditRecord(CreditRecord creditRecord) {
        return 0;
    }

    @Override
    public List<CreditRecord> selectCreditRecordById(int userid) {
        LinkedList<CreditRecord> creditRecords = new LinkedList<>();
        CreditRecord creditRecord = new CreditRecord();
        creditRecord.setOrderId(1);
        creditRecords.add(creditRecord);
        return creditRecords;
    }

    @Override
    public int updateUserCredit(double changeCredit, int userid) {
        return 0;
    }
}
