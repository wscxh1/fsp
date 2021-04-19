package com.foodshare.config;

import com.foodshare.config.security.*;
import com.foodshare.config.security.handler.AccessDecisionHandler;
import com.foodshare.config.security.handler.FilterInvocationSecurityMetadataSourceHandler;
import com.foodshare.config.security.handler.LoginFailureHandler;
import com.foodshare.config.security.handler.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Resource
    LoginSuccessHandler successHandler;

    @Resource
    LoginFailureHandler failureHandler;

    @Resource
    ValidateCaptchaFilter validateCaptchaFilter;

    @Resource
    FilterInvocationSecurityMetadataSourceHandler securityMetadataSourceHandler;

    @Resource
    AccessDecisionHandler accessDecisionHandler;

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService myUserService(){
        return new UserDetailServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserService())
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/login/**","/login","/register","/lib/**").permitAll()
//                .antMatchers("/home/**").authenticated() //任何请求,登录后可以访问
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error=-1001")
//                .permitAll() //登录页面用户任意访问
//                .and()
//                .logout().permitAll(); //注销行为任意访问

        // 禁用CSRF 开启跨域
        http.cors().and().csrf().disable();

        http.authorizeRequests()
                // HttpSecurity配置，可以通过URL获取对应的角色信息
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(securityMetadataSourceHandler);
                        object.setAccessDecisionManager(accessDecisionHandler);
                        return object;
                    }
                })
                .antMatchers("/login/**","/login","/register","/lib/**").permitAll()
                .and()
                // 开启表单登录，即登录页
                .formLogin()
                // 自定义登录页，未配置下启用默认登录页
                 .loginPage("/login")
                // 登录请求接口，默认为 "/login" 接口
                .loginProcessingUrl("/login")
                // 登录参数-用户名
                .usernameParameter("username")
                // 登录参数-密码
                .passwordParameter("password")
                // 登录成功回调函数-返回登陆成功的JSON信息
                .successHandler(successHandler)
                // 登录失败回调函数-返回登陆失败的JSON信息
                .failureHandler(failureHandler)
                // 和登录相关的接口都不需要认证即可访问
                .permitAll()
                .and()
                // 开启注销登录配置
                .logout()
                // 配置注销登录请求URL，默认为 "/logout"
                .logoutUrl("/logout")
                // 是否清除身份认证信息，默认为true，表示清除
                .clearAuthentication(true)
                // 是否使session失效，默认为true
                .invalidateHttpSession(true)
                // 删除指定的Cookie信息，可以传入多个key
                .deleteCookies("JSESSIONID")
                // 注销回调函数，可以处理数据清除工作
                .addLogoutHandler((request, response, authentication) -> {
                    System.out.println("-----logout_handler----->");
                })
                // 注销成功回调函数
                .logoutSuccessHandler((request, response, authentication) -> {
                    System.out.println("-----logout_success----->");
                    // 注销成功后重定向到登录页
                    response.sendRedirect("/login");
                });

        // 在用户名密码校验过滤器之前添加上验证码校验过滤器
        http.addFilterBefore(validateCaptchaFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
