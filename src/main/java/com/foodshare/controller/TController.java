package com.foodshare.controller;

import com.foodshare.entity.User;
import com.foodshare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/test")
public class TController {
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/t1/{name}")
    @ResponseBody
    public Object t1(@PathVariable("name") String name) {
//        HttpSession session = request.getSession();
//        session.setAttribute("hello","123123123");
//        return String.valueOf(session.getAttribute("hello"));
//        User user = new User(1,"SB萨比","616690324@qq.com",(byte)1,(byte)0,null,(byte)0,"123456","null");
//        userService.set(user);
        return userService.queryByName(name,1,1);
    }

    @RequestMapping("/t2")
    public String t2(HttpServletRequest request) {
        return "/login/login.html";
    }
}
