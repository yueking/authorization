package com.yueking.security.demo;

import com.yueking.security.core.entity.User;
import com.yueking.security.core.repository.UserDao;
import com.yueking.security.core.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Resource
    private UserDao userDao;
    @Autowired
    private UserService userService;
    @Test
    public void testUserInsert(){
       for(int i=0;i<10;i++){
           userService.add(new User("admin"+i));
       }
    }

    @Test
    public void testUserDelete(){
        userDao.deleteAll();
    }

    @Test
    public void testQuery() {
        // userDao.findAll()
    }
}
