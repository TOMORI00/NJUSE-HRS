package com.example.hotel.test.Stubs.DataStubs;

import com.example.hotel.data.admin.AdminMapper;
import com.example.hotel.po.User;

import java.util.LinkedList;
import java.util.List;

public class AdminMapperStub implements AdminMapper {
    @Override
    public int addStaff(User user) {
        return 0;
    }

    @Override
    public int addBusi(User user) {
        return 0;
    }

    @Override
    public List<User> getAllStaffs() {
        List<User> users = new LinkedList<>();
        User user = new User();
        user.setEmail("11111");
        users.add(user);
        return users;
    }

    @Override
    public List<User> getAllBusis() {
        List<User> users = new LinkedList<>();
        User user = new User();
        user.setEmail("11111");
        users.add(user);
        return users;
    }

    @Override
    public List<User> getAllClients() {
        List<User> users = new LinkedList<>();
        User user = new User();
        user.setEmail("11111");
        users.add(user);
        return users;
    }
}
