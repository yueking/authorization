package com.yueking.security.core.service;

import com.yueking.security.core.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {
    User add(User user);
    void deleteById(String id);
    User update(User user);
    User findById(String username);
    List<User> query(User user, Sort sort);
    Page<User> query(User user, Pageable pageable);

}
