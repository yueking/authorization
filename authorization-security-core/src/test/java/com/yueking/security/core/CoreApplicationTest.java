package com.yueking.security.core;

import com.yueking.security.core.entity.User;
import com.yueking.security.core.repository.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoreApplicationTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void userInsert(){
        userDao.save(new User("yueking"));
    }
}
