package com.yueking.security.core;

import com.yueking.security.core.entity.User;
import com.yueking.security.core.repository.UserDao;
import com.yueking.security.core.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoreApplicationTest {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Test
    public void userInsert(){
        for (int i = 0; i < 10; i++) {
            userService.add(new User("user"+i));
        }
    }

    @Test
    public void userQuery(){
        User user = new User();
        user.setUsername("user");
        List<User> query = userService.query(user, null);
        System.out.println(query.size());
    }
}
