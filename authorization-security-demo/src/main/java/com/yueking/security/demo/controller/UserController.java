package com.yueking.security.demo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.yueking.security.core.entity.Base;
import com.yueking.security.core.entity.User;
import com.yueking.security.core.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Page<User> query(User query, @PageableDefault(page = 0, size = 20, sort = "username",direction = Sort.Direction.ASC) Pageable pageable) throws JsonProcessingException {
        System.out.println("query:" + query);
        System.out.println("pageable:" + pageable);
        Page<User> resultPage = userService.query(query, pageable);
        ObjectMapper objectMapper = new JsonMapper();
        System.out.println("search:" + objectMapper.writeValueAsString(resultPage));
        return resultPage;
    }

    @JsonView(Base.SimpleView.class)
    @GetMapping("/query")
    public List<User> query(User user,@SortDefault(sort = "username",direction = Sort.Direction.ASC) Sort sort) {
        System.out.println("===");
        return userService.query(user, sort);
    }

    @JsonView(Base.DetailView.class)
    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        return userService.findById(id);
    }

    @JsonView(Base.DetailView.class)
    @PostMapping
    public User add(@Valid @RequestBody User user,BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error ->{
                System.out.println(error.getDefaultMessage());
            });
        }
        System.out.println("user:" + user);
        System.out.println("createDate:" + user.getCreatedDate());
        return userService.add(user);
    }

    @JsonView(Base.DetailView.class)
    @PutMapping
    public User update(@Valid @RequestBody User user, BindingResult error) {
        if (error.hasErrors()) {
            List<ObjectError> allErrors = error.getAllErrors();
            for (ObjectError allError : allErrors) {
                System.out.println(allError.getDefaultMessage());
            }
        }
        System.out.println("user:" + user);
        System.out.println("createDate:" + user.getCreatedDate());
        return userService.update(user);
    }

    @DeleteMapping ("/{id}")
    public void delete(@PathVariable String id) {
        System.out.println("delete:"+id);
         userService.deleteById(id);
    }


}
