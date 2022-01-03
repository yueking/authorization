package com.yueking.security.core.service;

import com.yueking.security.core.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {
    User findById(String username);
    void modify(User user);
    void add(User user);
    List<User> query(User user, Sort sort) ;
    Page<User> query(Example<User> userExample, Pageable pageable);
}
