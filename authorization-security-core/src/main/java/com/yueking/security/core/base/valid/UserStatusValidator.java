package com.yueking.security.core.base.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class UserStatusValidator implements ConstraintValidator<UserStatus, Integer> {

    @Override
    public void initialize(UserStatus constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        System.out.println("initialize--UserStatusValidator\t"+System.currentTimeMillis());
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if (integer == null) {
            return true;
        }
        Set<Integer> set = new HashSet<>();
        set.add(1000);
        set.add(1001);
        set.add(1002);
        return set.contains(integer);
    }
}
