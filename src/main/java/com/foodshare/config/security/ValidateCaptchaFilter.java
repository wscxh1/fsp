package com.foodshare.config.security;


import com.foodshare.service.impl.VerifyCodeServiceImpl;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ValidateCaptchaFilter extends OncePerRequestFilter {

    private AuthenticationFailureHandler authenticationFailureHandler;

    public ValidateCaptchaFilter(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    /**
     * 静态字段可以添加至单独的静态字段文件中
     */
    private static final String LOGIN_PATH = "/login";
    private static final String POST_METHOD = "post";


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 判断是否为请求登录接口，校验验证码
        if (LOGIN_PATH.equalsIgnoreCase(httpServletRequest.getRequestURI())
                && POST_METHOD.equalsIgnoreCase(httpServletRequest.getMethod())) {
            System.out.println(httpServletRequest.getRequestURI());
            System.out.println(httpServletRequest.getRequestURL());
            try {
                VerifyCodeServiceImpl.validateCode(httpServletRequest);
            } catch (AuthenticationException e) {
                e.printStackTrace();
                // 校验失败，调用认证失败处理逻辑
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }



}
