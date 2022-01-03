package com.yueking.security.core.service.impl;

import com.yueking.security.core.entity.User;
import com.yueking.security.core.repository.UserDao;
import com.yueking.security.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(String username) {
        return userDao.findById(username).get();
    }

    @Override
    public void modify(User user) {
        userDao.save(user);
    }

    @Override
    public void add(User user) {
        userDao.save(user);
    }

    @Override
    public List<User> query(User user, Sort sort) {
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("username", ExampleMatcher.GenericPropertyMatchers.startsWith());
        Example<User> userExample = Example.of(user, matcher);
        if (sort == null) {
            return userDao.findAll(userExample);
        }
        return userDao.findAll(userExample, sort);
    }
    @Override
    public Page<User> query(Example<User> userExample, Pageable pageable) {
        return userDao.findAll(userExample, pageable);
    }
}
