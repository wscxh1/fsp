package com.foodshare.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public abstract class AbstractSessionController {
    @Resource
    protected HttpSession session;

    @Resource
    protected HttpServletRequest request;

    @Resource
    protected HttpServletResponse response;

}
