package com.foodshare.interceptor;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;

import javax.servlet.*;
import java.io.IOException;

public class MySecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return null;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return null;
    }
}
