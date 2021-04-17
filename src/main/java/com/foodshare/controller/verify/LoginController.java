package com.foodshare.controller.verify;

import com.foodshare.controller.RequestLimit;
import com.foodshare.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/verify")
public class LoginController {

    private VerifyCodeService verifyCodeService;

    @Autowired
    public void setVerifyCodeService(VerifyCodeService verifyCodeService) {
        this.verifyCodeService = verifyCodeService;
    }

    @GetMapping("/login")
    public String login() {
        return "/verify/login.html";
    }

    @GetMapping("/register")
    public String register() {
        return "/verify/register.html";
    }

    @PostMapping("/login")
    public Object login(@RequestParam("email") String email, String password, String verifyCode) {
        //在session放入user对象
        //记住登录生成token，放到数据库
        return null;
    }

    @GetMapping("/login/code.jpg")
    @RequestLimit(count = 5,time = 10)
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String randomCode = verifyCodeService.getCode(verifyCodeService.getCodeCount());
        BufferedImage buffImg = verifyCodeService.getImage(randomCode);
        // 将四位数字的验证码保存到Session中。
        HttpSession session = request.getSession();
        session.setAttribute("loginValidateCode", randomCode);
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();
    }

    public boolean autoLoginChecker(HttpServletRequest request) {

        return false;
    }
}
