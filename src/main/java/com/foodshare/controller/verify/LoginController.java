package com.foodshare.controller.verify;

import com.foodshare.controller.RequestLimit;
import com.foodshare.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class LoginController {

    private VerifyCodeService verifyCodeService;
    private static final String PICTURE_CONTENT_TYPE_JPEG = "image/jpeg";
    public static final String SESSION_KEY_VERIFY_CODE = "SESSION_KEY_VERIFY_CODE";
    public static final String SESSION_KEY_VERIFY_CODE_TIME = "SESSION_KEY_VERIFY_CODE_TIME";

    @Autowired
    public void setVerifyCodeService(VerifyCodeService verifyCodeService) {
        this.verifyCodeService = verifyCodeService;
    }

    @GetMapping("/login")
    public String login() {
        return "/verify/login.html";
    }

    @GetMapping("/register")
    @PreAuthorize("hasRole('1')")
    public String register() {
        return "/verify/register.html";
    }


    @GetMapping("/login/code.jpg")
    @RequestLimit(count = 5,time = 10)
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String randomCode = verifyCodeService.getCode(verifyCodeService.getCodeCount());
        BufferedImage buffImg = verifyCodeService.getImage(randomCode);
        // 将四位数字的验证码保存到Session中。
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_KEY_VERIFY_CODE, randomCode);
        session.setAttribute(SESSION_KEY_VERIFY_CODE_TIME, System.currentTimeMillis());

        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        response.setContentType(PICTURE_CONTENT_TYPE_JPEG);
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();
    }


}
