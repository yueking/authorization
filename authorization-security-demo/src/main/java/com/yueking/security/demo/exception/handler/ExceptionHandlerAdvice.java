package com.yueking.security.demo.exception.handler;

import com.yueking.security.demo.exception.UserNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public String handleEx(BindException e) {
        StringBuilder stringBuilder = new StringBuilder();
        List<FieldError> fieldErrors = e.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            stringBuilder.append("属性:").append(fieldError.getField()).append("校验不通过:").append(fieldError.getDefaultMessage());
        }
        return stringBuilder.toString();
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public List<String> handleEx(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<String> list = constraintViolations.stream().map(v -> "属性:" + v.getPropertyPath() + ",属性值:" + v.getInvalidValue() + ",校验不通过信息:" + v.getMessage() + ",MessageTemplate:" + v.getMessageTemplate()).collect(Collectors.toList());
        return list;
    }

    @ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleEx(UserNotExistException e) {
        System.out.println("===exception");
        Map<String,Object> result = new HashMap<>();
        result.put("username", e.getUsername());
        result.put("message", e.getMessage());
        return result;
    }
}
