package com.foodshare.config.security.handler;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AccessDecisionHandler implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection)
            throws AccessDeniedException, InsufficientAuthenticationException {

        // 循环处理元数据处理器中返回的权限集合
        for (ConfigAttribute ca : collection) {
            // 获取当前请求需要的权限
            String needRole = ca.getAttribute();

            // 从登陆认证信息中获取当前用户所具有的权限
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

            // 判断拥有的权限中是否包含所需的权限，如果拥有任一所需的权限就表示拥有访问权
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(needRole)) {
                    return;
                }
            }
        }

        throw new AccessDeniedException("权限不足!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}