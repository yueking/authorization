package com.yueking.security.demo.exception;

import lombok.Data;

@Data
public class UserNotExistException extends RuntimeException{
    private String username;

    public UserNotExistException(String username) {
        super("user not exist");
        this.username = username;
    }
}
