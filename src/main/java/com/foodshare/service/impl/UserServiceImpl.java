package com.foodshare.service.impl;

import com.foodshare.entity.User;
import com.foodshare.entity.UserExample;
import com.foodshare.mapper.UserMapper;
import com.foodshare.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    public static final int EXIST_CODE_NICKNAME = -1000;
    public static final int EXIST_CODE_EMAIL = -1001;

    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User get(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int set(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> queryByName(String name, int num, int page) {
        UserExample example = new UserExample();
        example.or()
                .andNicknameLike(name);
        return userMapper.selectByExampleWithRowbounds(example,
                new RowBounds(getOffset(num, page), num)
        );
    }

    @Override
    public int[] checkUnique(String email,String nickname) {
        int[] items = new int[2];
        UserExample example = new UserExample();

        return items;
    }

    @Override
    public boolean isEmailExist(String email) {

        return false;
    }

    @Override
    public boolean isNickNameExist(String email) {
        return false;
    }

    private int getOffset(int num, int page) {
        return (Math.max(page, 1) - 1) * num;
    }



}
