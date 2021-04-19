package com.foodshare.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodshare.model.ApiResp;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.foodshare.model.BaseEnumError.SYSTEM_ARGUMENT_NOT_VALID;

@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Resource
    ObjectMapper objectMapper;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException {
        objectMapper.writeValue(httpServletResponse.getOutputStream(),
                ApiResp.retFail(SYSTEM_ARGUMENT_NOT_VALID,
                        e.getMessage()));

    }
}
