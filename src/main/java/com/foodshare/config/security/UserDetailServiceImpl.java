package com.foodshare.config.security;

import com.foodshare.entity.User;
import com.foodshare.entity.UserExample;
import com.foodshare.mapper.UserMapper;
import com.foodshare.service.impl.exception.UserNotFoundException;
import org.apache.ibatis.session.RowBounds;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.annotation.Resource;

public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("==================> nickname :" + s);
        UserExample userExample= new UserExample();
        userExample.or().andNicknameEqualTo(s);
        User user = userMapper.selectByExampleWithRowbounds(userExample,new RowBounds(0,1)).get(0);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getNickname())
                .password(user.getPswd())
                .disabled(false)
                .accountExpired(false)
                .credentialsExpired(false)
                .accountLocked(false)
                .authorities(String.valueOf(user.getUsertype())).build();
    }




}
