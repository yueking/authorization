package com.yueking.security.core.service;

import com.yueking.security.core.entity.User;

import java.util.List;

public interface UserService {
    User findById(String username);
    void deleteById(String id);
    void add(User user);
    void update(User user);
    List<User> query(User user);

}
