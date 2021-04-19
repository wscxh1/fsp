package com.foodshare.config.security;

import org.springframework.security.core.AuthenticationException;

public class ValidateCaptchaException extends AuthenticationException {

    public ValidateCaptchaException(String message) {
        super(message);
    }
}

