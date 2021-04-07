package com.foodshare.service;

import com.foodshare.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    User get(int id);

    int set(User user);

}
