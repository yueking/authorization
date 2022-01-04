package com.yueking.security.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.yueking.security.core.entity.Base;
import com.yueking.security.core.entity.Permission;
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

    @JsonView(Base.SimpleView.class)
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

    @JsonView(Base.DetailView.class)
    @GetMapping("/user/{id}")
    public User findUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @JsonView(Base.DetailView.class)
    @GetMapping("/perm")
    public Permission perm() {
        Permission permission = new Permission();
        permission.setId("pId");
        permission.setPermName("name");
        permission.setPermTag("tag");
        permission.setPermDesc("desc");
        return permission;
    }
}
