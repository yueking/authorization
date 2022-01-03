package com.yueking.security.core.service.impl;

import com.yueking.security.core.entity.User;
import com.yueking.security.core.repository.UserDao;
import com.yueking.security.core.service.UserService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.*;

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
    public void add(User user) {
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        userDao.save(user);
    }

    // public List<User> query(User user) {
    //     user.setUsername("user5");
    //     Specification<User> specification = new Specification<User>() {
    //         @Override
    //         public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
    //             Path<Object> username = root.get("username");
    //             Path<Object> createdDate = root.get("createdDate");
    //             Predicate predicateUsername = criteriaBuilder.equal(username, user.getUsername());
    //             criteriaQuery.where(predicateUsername);
    //             return criteriaQuery.getRestriction();
    //         }
    //     };
    //     return userDao.findAll(specification);
    // }

    public List<User> query(User user) {
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                // root = criteriaQuery.from(User.class);
                Path<String> usernameExp = root.get("username");
                Path<Boolean> delExp = root.get("del");

                Predicate predicateUsername = criteriaBuilder.like(usernameExp, "%" + user.getUsername() + "%");
                Predicate predicateDel = criteriaBuilder.equal(delExp, user.isDel());

                predicateList.add(predicateUsername);
                predicateList.add(predicateDel);

                if (user.getStartDate() != null) {
                    Path<Date> createdDateExp = root.get("createdDate");
                    // Predicate predicateCreate = criteriaBuilder.greaterThan(createdDateExp, user.getStartDate());
                    // predicateList.add(predicateCreate);
                    // Predicate predicateCreate = criteriaBuilder.between(createdDateExp, user.getStartDate(),user.getEndDate());
                    Predicate predicateCreate = criteriaBuilder.between(createdDateExp, user.getStartDate(),new Date(user.getEndDate().getTime()+24*3600*1000));
                    predicateList.add(predicateCreate);
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
        return userDao.findAll(specification);
    }


}
