package com.foodshare.service.impl;

import com.foodshare.entity.User;
import com.foodshare.entity.UserExample;
import com.foodshare.mapper.UserMapper;
import com.foodshare.service.UserService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    public static final String SESSION_USER_TAG = "FSP_LOGIN_USER";
    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getByNickName(String nickname) {
        UserExample example = new UserExample();
        example.or()
                .andNicknameEqualTo(nickname);
        try {
            return userMapper.selectByExampleWithRowbounds(example,
                    new RowBounds(0, 1)
            ).get(0);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.insertSelective(user);
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
    public boolean isUnique(String email, String nickname) {
        UserExample example = new UserExample();
        example.or().andNicknameEqualTo(nickname);
        example.or().andEmailEqualTo(email);
        return userMapper.countByExample(example) > 1;
    }

    @Override
    public boolean isEmailUnique(String email) {
        UserExample example = new UserExample();
        example.or().andEmailEqualTo(email);
        return userMapper.countByExample(example) > 1;
    }

    @Override
    public boolean isNickNameUnique(String nickname) {
        UserExample example = new UserExample();
        example.or().andNicknameEqualTo(nickname);
        return userMapper.countByExample(example) > 1;
    }

    @Override
    public User getSessionUser(HttpSession session) {
        User user = (User) session.getAttribute(SESSION_USER_TAG);
        if (user == null) {
            SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
            if (securityContext != null) {
                String userName = ((UserDetails) securityContext.getAuthentication().getPrincipal()).getUsername();
                user = getByNickName(userName);
                if (user != null) {
                    addUserToSession(session, user);
                }
            }
        }
        return user;
    }

    @Override
    public void addUserToSession(HttpSession session, User user) {
        session.setAttribute(SESSION_USER_TAG, user);
    }


    private int getOffset(int num, int page) {
        return (Math.max(page, 1) - 1) * num;
    }
}
