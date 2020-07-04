package com.example.hotel.test.Unit.data;

import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.User;
import com.example.hotel.test.BasicTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

public class AccountMapperTest extends BasicTest {
    @Autowired
    private AccountMapper accountMapper;

    @Test
    @Transactional
    @Rollback
    //正常创建账号
    public void createNewAccountTest1() {
        User user = new User();
        user.setEmail("createNewAccount1_test@qq.com");
        user.setPassword("123456");
        user.setCredit(100);
        user.setPhoneNumber("11111111111");
        user.setUserName("createNewAccount_test");
        user.setUserType(UserType.Client);
        try {
            accountMapper.createNewAccount(user);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @Transactional
    @Rollback
    //创建相同邮箱的账号
    public void createNewAccountTest2() {
        User user1 = new User();
        user1.setEmail("createNewAccount2_1_test@qq.com");
        user1.setPassword("123456");
        user1.setCredit(100);
        user1.setPhoneNumber("11111111111");
        user1.setUserName("createNewAccount2_1");
        user1.setUserType(UserType.Client);
        accountMapper.createNewAccount(user1);

        User user2 = new User();
        user2.setEmail("createNewAccount2_1_test@qq.com");
        user2.setPassword("123456");
        user2.setCredit(100);
        user2.setPhoneNumber("11111111111");
        user2.setUserName("createNewAccount2_2");
        user2.setUserType(UserType.Client);
        try {
            accountMapper.createNewAccount(user2);
            fail();
        } catch (Exception e) {
            System.out.println("账号已存在");
            assertTrue(true);
        }
    }

    @Test
    //正常根据邮箱获取账号
    public void getAccountByNameTest1() {
        User user = accountMapper.getAccountByName("123@qq.com");
        assertEquals(5, (int) user.getId());
    }

    @Test
    //获取未注册邮箱的账号
    public void getAccountByNameTest2() {
        User user = accountMapper.getAccountByName("Not_Exist@qq.com");
        assertNull(user);
    }

    @Test
    //正常根据Id获取账号
    public void getAccountByIdTest1() {
        User user = accountMapper.getAccountById(5);
        assertEquals("123@qq.com", user.getEmail());
    }

    @Test
    //获取不存在的账号
    public void getAccountByIdTest2() {
        User user = accountMapper.getAccountById(-1);
        assertNull(user);
    }

    @Test
    @Transactional
    @Rollback
    //正常更新账户信息
    public void updateAccountTest1() {
        accountMapper.updateAccount(5, "111111", "updateAccountTest1", "98765432100");
        User user = accountMapper.getAccountById(5);
        assertEquals("111111", user.getPassword());
        assertEquals("updateAccountTest1", user.getUserName());
        assertEquals("98765432100", user.getPhoneNumber());
    }

}
