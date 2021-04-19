package com.foodshare.service.impl.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFoundException extends UsernameNotFoundException {
    public UserNotFoundException() {
        super("不能找到用户");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
