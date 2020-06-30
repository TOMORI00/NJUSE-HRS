package com.example.hotel.data.credit;

import com.example.hotel.po.CreditRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Daiqj
 */
@Mapper
@Repository
public interface CreditMapper {

    /**
     * 插入信用记录
     *
     * @param creditRecord 信用记录PO
     * @return int
     * @author ljl
     */
    int insertCreditRecord(CreditRecord creditRecord);

    /**
     * 根据userid返回信用记录列表
     *
     * @param userid 用户id
     * @return 信用记录列表
     * @author ljl
     */
    List<CreditRecord> selectCreditRecordById(@Param("userid") int userid);

    /**
     * 改变user的信用值  credit=credit+changeCredit
     *
     * @param changeCredit 改变的信用值
     * @param userid       用户ID
     * @return int
     * @author ljl
     */
    int updateUserCredit(@Param("changeCredit") double changeCredit, @Param("userid") int userid);
}