package com.example.hotel.test.Stubs.DataStubs;

import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.User;

public class AccountMapperStub implements AccountMapper {
    @Override
    public int createNewAccount(User user) {
        return 0;
    }

    @Override
    public User getAccountByName(String email) {
        return null;
    }

    @Override
    public User getAccountById(int id) {
        return null;
    }

    @Override
    public int updateAccount(int id, String password, String username, String phonenumber) {
        return 0;
    }
}
