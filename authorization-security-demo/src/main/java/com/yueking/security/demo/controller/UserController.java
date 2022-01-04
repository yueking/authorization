package com.yueking.security.demo.controller;

import com.yueking.security.core.entity.User;
import com.yueking.security.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/user", method = RequestMethod.GET)

    public List<User> query(User query, Pageable pageable) {
        System.out.println("============");
        System.out.println("query:" + query);
        System.out.println("pageable:" + pageable);
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        return userList;
    }

    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable String id) {
        return userService.findById(id);
    }
}
