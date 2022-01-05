package com.yueking.security.core.service.impl;

import com.yueking.security.core.base.JsonPage;
import com.yueking.security.core.entity.User;
import com.yueking.security.core.repository.UserDao;
import com.yueking.security.core.service.UserService;
import com.yueking.security.core.specification.UserSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findById(String id) {
        return userDao.findById(id).get();
    }

    @Override
    public void deleteById(String id) {
        userDao.deleteById(id);
    }

    @Override
    public User add(User user) {
       return userDao.save(user);
    }

    @Override
    public User update(User user) {
        return userDao.save(user);
    }

    public List<User> query(User user, Sort sort) {
        Specification specification = new UserSpecification(user);
        return userDao.findAll(specification,sort);
    }

    public Page<User> query(User user, Pageable pageable) {
        Page page = userDao.findAll(new UserSpecification(user), pageable);
        // return new JsonPage<User>(page,pageable);
        return new JsonPage<User>(page.getContent(),pageable,page.getTotalElements());
    }


}
