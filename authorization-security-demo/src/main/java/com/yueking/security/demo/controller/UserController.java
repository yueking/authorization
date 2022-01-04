package com.yueking.security.demo.controller;

import com.yueking.security.core.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<User> query(User query, Pageable pageable) {
        System.out.println("query:"+query);
        System.out.println("pageable:"+pageable);
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;
    }

    @RequestMapping(value = "")
    public List<User> query2(User user) {
        return null;
    }
}
