package com.yueking.security.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yueking.security.core.entity.Base;
import com.yueking.security.core.entity.Permission;
import com.yueking.security.core.entity.User;
import com.yueking.security.core.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @JsonView(Base.SimpleView.class)
    @GetMapping
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
    @GetMapping("/{id}")
    public User findUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @JsonView(Base.DetailView.class)
    @PostMapping
    public User addUser(@RequestBody User user) {
        System.out.println("user:"+user);
        System.out.println("createDate:"+user.getCreatedDate());
        // return userService.add(user);
        return user;
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
