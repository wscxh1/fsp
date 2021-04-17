package com.foodshare.service;

import com.foodshare.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    User get(int id);

    int set(User user);

    List<User> queryByName(String name, int num, int page);

    public int[] checkUnique(String email,String nickname);

    public boolean isEmailExist(String email);

    public boolean isNickNameExist(String email);


}
