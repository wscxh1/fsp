package com.foodshare.controller;

import org.springframework.core.NestedRuntimeException;

public class RequestLimitException extends NestedRuntimeException {

    public RequestLimitException(){
        super("HTTP请求超出设定的限制");
    }

    public RequestLimitException(String msg) {
        super(msg);
    }
}