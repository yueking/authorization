package com.yueking.security.core.base.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {
    // @Autowired
    // UserService userService;

    // @Autowired
    // UserDao userDao;
    // @Autowired
    // UserService userService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        System.out.println("==initialize");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String username = (String) o;
        System.out.printf("==isValid");
        // System.out.println("userService:"+userService);
        // return  !userService.existsById(username);
        // System.out.println("userDao:"+userDao);
        // System.out.println("userService:"+userService);
        return true;
    }
}
