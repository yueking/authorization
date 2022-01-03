package com.yueking.security.core;

import com.yueking.security.core.entity.User;
import com.yueking.security.core.repository.UserDao;
import com.yueking.security.core.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
    public void userQuery() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse("2022-01-04");
        Date endDate = format.parse("2022-01-07");

        System.out.println(startDate);

        User user = new User();
        user.setUsername("user");
        user.setDel(false);
        user.setStartDate(startDate);
        user.setEndDate(endDate);
        System.out.println("startDate:"+startDate.toString());

        Sort sort = Sort.by(Sort.Direction.DESC, "username");
        List<User> query = userService.query(user,sort);
        System.out.println(query.size());
        for (User user1 : query) {
            System.out.println(user1.getUsername()+"\t"+user1.getCreatedDate());
        }
    }

    @Test
    public void userQueryPageable() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = format.parse("2022-01-04");
        Date endDate = format.parse("2022-01-07");

        System.out.println(startDate);

        User user = new User();
        user.setUsername("user");
        user.setDel(false);
        user.setStartDate(startDate);
        user.setEndDate(endDate);
        System.out.println("startDate:"+startDate.toString());


        Sort sort = Sort.by(Sort.Direction.DESC, "username");
        // Pageable pageable = PageRequest.of(0, 2,sort);
        Pageable pageable = PageRequest.of(0, 2);

        Page<User> userPage = userService.query(user, pageable);
        System.out.println(userPage);

        System.out.println("总条数："+userPage.getTotalElements());
        System.out.println("总页数："+userPage.getTotalPages());

        List<User> content = userPage.getContent();
        for (User user1 : content) {
            System.out.println(user1.getUsername());
        }

    }
}
