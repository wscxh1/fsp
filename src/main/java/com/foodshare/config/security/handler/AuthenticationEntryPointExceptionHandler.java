package com.foodshare.config.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 匿名未登录用户异常处理
 */
public class AuthenticationEntryPointExceptionHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {

        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write("用户未登录，请先登录");
    }
}