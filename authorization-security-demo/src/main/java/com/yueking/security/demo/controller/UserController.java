package com.yueking.security.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.yueking.security.core.base.JsonPage;
import com.yueking.security.core.entity.Base;
import com.yueking.security.core.entity.User;
import com.yueking.security.core.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @JsonView(Base.SimpleView.class)
    @GetMapping
    public Page<User> query(User query, @PageableDefault(page = 0,size = 20) Pageable pageable) throws JsonProcessingException {
        System.out.println("query:" + query);
        System.out.println("pageable:" + pageable);
        Page<User> resultPage =  userService.query(query, pageable);
        ObjectMapper objectMapper = new JsonMapper();
        System.out.println("search:"+objectMapper.writeValueAsString(resultPage));
        return resultPage;
    }

    @JsonView(Base.DetailView.class)
    @GetMapping("/{id}")
    public User findUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @JsonView(Base.DetailView.class)
    @PostMapping
    public User addUser(@Valid @RequestBody User user) {
        System.out.println("user:"+user);
        System.out.println("createDate:"+user.getCreatedDate());
        return userService.add(user);
    }


}
