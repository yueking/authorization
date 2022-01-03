package com.yueking.security.core;

import com.yueking.security.core.entity.User;
import com.yueking.security.core.repository.UserDao;
import com.yueking.security.core.service.UserService;
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

    @Autowired
    private UserService userService;

    @Test
    public void userDel(){
        userDao.deleteAll();
    }

    @Test
    public void userById(){
        User user2 = userService.findById("user2");
        System.out.println(user2);
    }

    @Test
    public void userDelById() {
        userService.deleteById("user1");
    }
    @Test
    public void userInsert(){
        for (int i = 0; i < 10; i++) {
            userService.add(new User("user"+i));
        }
    }

    @Test
    public void userQuery(){

    }
}
