package com.foodshare.config.security;


import com.foodshare.controller.verify.LoginController;
import com.foodshare.service.impl.VerifyCodeServiceImpl;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class ValidateCaptchaFilter extends OncePerRequestFilter {

    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 静态字段可以添加至单独的静态字段文件中
     */
    private static final String LOGIN_PATH = "/login";
    private static final String POST_METHOD = "post";
    private static final String CAPTCHA_IS_EMPTY = "Captcha is empty";
    private static final String CAPTCHA_NOT_EXISTED = "Captcha not existed";
    private static final String CAPTCHA_IS_EXPIRED = "Captcha is expired";
    private static final String CAPTCHA_NOT_MATCHED = "Captcha not matched";
    private static final String CAPTCHA = "verifyCode";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 判断是否为请求登录接口，校验验证码
        if (LOGIN_PATH.equalsIgnoreCase(httpServletRequest.getRequestURI())
                && POST_METHOD.equalsIgnoreCase(httpServletRequest.getMethod())) {
            try {
                validateCode(httpServletRequest);
            } catch (AuthenticationException e) {
                e.printStackTrace();
                // 校验失败，调用认证失败处理逻辑
                authenticationFailureHandler.onAuthenticationFailure(httpServletRequest, httpServletResponse, e);
                return;
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void validateCode(HttpServletRequest httpServletRequest) throws AuthenticationException {
        HttpSession session = httpServletRequest.getSession();
        // 获取 session 中的验证码
        String codeInSession = (String) session.getAttribute(LoginController.SESSION_KEY_VERIFY_CODE);
        Long codeTime = (Long) session.getAttribute(LoginController.SESSION_KEY_VERIFY_CODE_TIME);
        // 获取请求中的验证码
        String codeInRequest = httpServletRequest.getParameter(CAPTCHA);

        if (StringUtils.isEmpty(codeInRequest)) {
            throw new ValidateCaptchaException(CAPTCHA_IS_EMPTY);
        }
        if (codeInSession == null) {
            throw new ValidateCaptchaException(CAPTCHA_NOT_EXISTED);
        }
        boolean isExpire = (System.currentTimeMillis() - codeTime) > 60000;
        System.out.println(codeTime);
        if (isExpire) {
            removeCode(session);
            throw new ValidateCaptchaException(CAPTCHA_IS_EXPIRED);
        }
        if (!codeInSession.equalsIgnoreCase(codeInRequest)) {
            throw new ValidateCaptchaException(CAPTCHA_NOT_MATCHED);
        }

        removeCode(session);
    }

    private void removeCode(HttpSession session) {
        session.removeAttribute(LoginController.SESSION_KEY_VERIFY_CODE);
        session.removeAttribute(LoginController.SESSION_KEY_VERIFY_CODE_TIME);
    }


}
