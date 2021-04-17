package com.foodshare.config;

import com.foodshare.interceptor.MySecurityInterceptor;
import com.foodshare.service.AuthUserService;
import com.foodshare.service.UserService;
import com.foodshare.service.impl.AuthUserServiceImpl;
import com.foodshare.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private MySecurityInterceptor mySecurityInterceptor;

    @Bean
    UserDetailsService userService() {
        return new AuthUserServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.userDetailsService(userService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
    }
}
