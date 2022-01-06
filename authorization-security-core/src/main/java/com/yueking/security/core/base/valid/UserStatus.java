package com.yueking.security.core.base.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserStatusValidator.class)
public @interface UserStatus {
    String message() default "status必须是1000/1001/1002";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
