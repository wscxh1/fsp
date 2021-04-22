package com.foodshare.controller.verify;

import com.foodshare.controller.AbstractSessionController;
import com.foodshare.controller.RequestLimit;
import com.foodshare.entity.User;
import com.foodshare.model.ApiResp;
import com.foodshare.model.BaseEnumError;
import com.foodshare.service.UserService;
import com.foodshare.service.VerifyCodeService;
import com.foodshare.service.impl.VerifyCodeServiceImpl;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static com.foodshare.model.BaseEnumError.SYSTEM_ARGUMENT_NOT_VALID;

@Controller
@RequestMapping("/")
public class LoginController extends AbstractSessionController {
    private static final String PICTURE_CONTENT_TYPE_JPEG = "image/jpeg";
    public static final String SESSION_KEY_VERIFY_CODE = "SESSION_KEY_VERIFY_CODE";
    public static final String SESSION_KEY_VERIFY_CODE_TIME = "SESSION_KEY_VERIFY_CODE_TIME";

    @Resource
    private VerifyCodeService verifyCodeService;
    @Resource
    private UserService userService;
    @Resource
    BCryptPasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String login() {
        return "/verify/login.html";
    }


    @GetMapping("/checkLoginStatus")
    @ResponseBody
    public Object checkLoginStatus() {
        User user = userService.getSessionUser(session);
        if (user == null) return ApiResp.retFail(BaseEnumError.SYSTEM_NO_LOGIN);
        user.setPswd("");
        return ApiResp.retOK(user);
    }

    @PostMapping("/register")
    @ResponseBody
    @RequestLimit(count = 10)
    public Object register(User user, HttpServletRequest request, HttpServletResponse response) {
        try {
            VerifyCodeServiceImpl.validateCode(request);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return ApiResp.retFail(SYSTEM_ARGUMENT_NOT_VALID,
                    e.getMessage());
        }
        boolean isNotValid = user == null ||
                user.getPswd() == null ||
                user.getEmail() == null ||
                user.getNickname() == null;
        if (!isNotValid)isNotValid = !userService.isUnique(user.getEmail(),user.getNickname());
        if (isNotValid) return ApiResp.retFail(SYSTEM_ARGUMENT_NOT_VALID);
        System.out.println("ASASASAS:    " + user.getNickname());
        user.setPswd(passwordEncoder.encode(user.getPswd().trim()));
        user.setUsertype((byte)2);
        user.setIsreal((byte)0);
        user.setIsdeleted((byte)0);
        userService.addUser(user);
        return ApiResp.retOK();
    }

    @GetMapping("/register/isNicknameUnique")
    @ResponseBody
    @RequestLimit(count = 30, time = 120)
    public Object isUserNameUnique(String nickname, HttpServletRequest request, HttpServletResponse response) {
        if (nickname == null || !userService.isNickNameUnique(nickname)) {
            return ApiResp.retOK("no");
        }
        return ApiResp.retOK("yes");
    }

    @GetMapping("/register/isEmailUnique")
    @ResponseBody
    @RequestLimit(count = 30, time = 120)
    public Object isEmailUnique(String email, HttpServletRequest request, HttpServletResponse response) {
        if (email == null || !userService.isEmailUnique(email)) {
            return ApiResp.retOK("no");
        }
        return ApiResp.retOK("yes");
    }


    @GetMapping("/login/code")
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
