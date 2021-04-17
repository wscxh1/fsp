package com.foodshare.service.impl;

import com.foodshare.entity.User;
import com.foodshare.entity.UserExample;
import com.foodshare.mapper.UserMapper;
import com.foodshare.service.AuthUserService;
import com.foodshare.service.UserService;
import com.foodshare.service.impl.exception.UserNotFoundException;
import com.foodshare.utils.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class AuthUserServiceImpl implements UserDetailsService {

    @Resource
    UserMapper userMapper;
    @Resource
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserExample userExample= new UserExample();
        userExample.or().andNicknameLike(s);
        User user = userMapper.selectByExampleWithRowbounds(userExample,new RowBounds(0,1)).get(0);
        if (user == null) {
            throw new UserNotFoundException();
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(user.getUsertype())));
        return new org.springframework.security.core.userdetails.User(
                user.getNickname(),
                user.getPswd(),
                authorities
        );
    }




}
