package com.yueking.security.core.service.impl;

import com.yueking.security.core.entity.User;
import com.yueking.security.core.repository.UserDao;
import com.yueking.security.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public List<User> query(Example<User> userExample, Sort sort) {
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
