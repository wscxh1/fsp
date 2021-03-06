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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
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
    FilterInvocationSecurityMetadataSourceHandler securityMetadataSourceHandler;

    @Resource
    AccessDecisionHandler accessDecisionHandler;

    @Resource
    AuthenticationFailureHandler authenticationFailureHandler;

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
//                .antMatchers("/home/**").authenticated() //????????????,?????????????????????
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error=-1001")
//                .permitAll() //??????????????????????????????
//                .and()
//                .logout().permitAll(); //????????????????????????

        // ??????CSRF ????????????
        http.cors().and().csrf().disable();

        http.authorizeRequests()
                // HttpSecurity?????????????????????URL???????????????????????????
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
                // ?????????????????????????????????
                .formLogin()
                // ??????????????????????????????????????????????????????
                 .loginPage("/login")
                // ?????????????????????????????? "/login" ??????
                .loginProcessingUrl("/login")
                // ????????????-?????????
                .usernameParameter("username")
                // ????????????-??????
                .passwordParameter("password")
                // ????????????????????????-?????????????????????JSON??????
                .successHandler(successHandler)
                // ????????????????????????-?????????????????????JSON??????
                .failureHandler(failureHandler)
                // ??????????????????????????????????????????????????????
                .permitAll()
                .and()
                // ????????????????????????
                .logout()
                // ????????????????????????URL???????????? "/logout"
                .logoutUrl("/logout")
                // ??????????????????????????????????????????true???????????????
                .clearAuthentication(true)
                // ?????????session??????????????????true
                .invalidateHttpSession(true)
                // ???????????????Cookie???????????????????????????key
                .deleteCookies("JSESSIONID")
                // ???????????????????????????????????????????????????
                .addLogoutHandler((request, response, authentication) -> {
                    System.out.println("-----logout_handler----->");
                })
                // ????????????????????????
                .logoutSuccessHandler((request, response, authentication) -> {
                    System.out.println("-----logout_success----->");
                    // ????????????????????????????????????
                    response.sendRedirect("/login");
                });

        // ????????????????????????????????????????????????????????????????????????
        http.addFilterBefore(new ValidateCaptchaFilter(authenticationFailureHandler), UsernamePasswordAuthenticationFilter.class);
    }
}
