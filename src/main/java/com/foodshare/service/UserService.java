package com.foodshare.service;

import com.foodshare.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {
    User getById(int id);

    User getByNickName(String nickname);

    int addUser(User user);

    int updateUser(User user);

    List<User> queryByName(String name, int num, int page);

    public boolean isUnique(String email, String nickname);

    public boolean isEmailUnique(String email);

    public boolean isNickNameUnique(String email);

    public User getSessionUser(HttpSession session);

    public void addUserToSession(HttpSession session, User user);

}
