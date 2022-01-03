package com.yueking.security.demo;

import com.yueking.security.core.entity.User;
import com.yueking.security.core.repository.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Resource
    private UserDao userDao;
    @Test
    public void testUserInsert(){
       for(int i=0;i<10;i++){
           userDao.save(new User("user" + i));
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
