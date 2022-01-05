package com.yueking.security.core.base.valid;

import com.yueking.security.core.repository.UserDao;
import com.yueking.security.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// @Component
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        System.out.println("==initialize");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String username = (String) o;
        // System.out.println("==isValid"+new Date());
        System.out.println("==isValid"+System.currentTimeMillis());
        if (userDao != null) {
            System.out.println("userDao:"+userDao);
            System.out.println("userService:"+userService);
            return  !userService.existsById(username);
        }
        return true;
    }
}
