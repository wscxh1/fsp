package com.foodshare.service.impl;

import com.foodshare.config.security.ValidateCaptchaException;
import com.foodshare.controller.verify.LoginController;
import com.foodshare.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {
    private static final String CAPTCHA_IS_EMPTY = "Captcha is empty";
    private static final String CAPTCHA_NOT_EXISTED = "Captcha not existed";
    private static final String CAPTCHA_IS_EXPIRED = "Captcha is expired";
    private static final String CAPTCHA_NOT_MATCHED = "Captcha not matched";
    private static final String CAPTCHA = "verifyCode";

    @Component
    @ConfigurationProperties(prefix = "verify-code")
    static class VerifyCode {
        private int width;
        private int height;
        private int codeCount;
        private int maxTone;
        private String fontName;
        private int fontStyle;
        private char[] codeSequence;

        public void setFontStyle(int fontStyle) {
            this.fontStyle = (fontStyle < 0 || fontStyle > 2)?Font.PLAIN : fontStyle;
        }

        public void setMaxTone(int maxTone) {
            this.maxTone = maxTone;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setCodeCount(int codeCount) {
            this.codeCount = codeCount;
        }

        public void setFontName(String fontName) {
            this.fontName = fontName;
        }

        public void setCodeSequence(char[] codeSequence) {
            this.codeSequence = codeSequence;
        }
    }

    VerifyCode verifyCode;
    private final Random random = new Random();

    @Autowired
    public void setVerifyCode(VerifyCode verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public String getCode(int length) {
        StringBuilder randomCode = new StringBuilder();
        for (int i = 0; i < length; i++) {
            randomCode.append(verifyCode.codeSequence[random.nextInt(36)]);
        }
        return randomCode.toString();
    }

    @Override
    public BufferedImage getImage(String code) {
        //height - 10 集中显示验证码
        int fontHeight = verifyCode.height - 7;
        int codeX = (verifyCode.width - 7) / (verifyCode.codeCount + 1);
        int codeY = verifyCode.height - 9;
        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(verifyCode.width, verifyCode.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D gd = buffImg.createGraphics();
        // 创建一个随机数生成器类
        // 将图像填充为白色
        gd.setColor(Color.LIGHT_GRAY);
        gd.fillRect(0, 0, verifyCode.width, verifyCode.height);
        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font(verifyCode.fontName, verifyCode.fontStyle, fontHeight);
        // 设置字体。
        gd.setFont(font);
        // 画边框。
//        gd.setColor(Color.BLACK);
//        gd.drawRect(0, 0, verifyCode.width - 1, verifyCode.height - 1);
        // 随机产生160条干扰线，使图象中的认证码不易被其它程序探测到。
        gd.setColor(Color.gray);
        for (int i = 0; i < 16; i++) {
            int x = random.nextInt(verifyCode.width);
            int y = random.nextInt(verifyCode.height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            gd.drawLine(x, y, x + xl, y + yl);
        }
        int red, green, blue;
        for (int i = 0; i < code.length(); i++) {
            red = random.nextInt(verifyCode.maxTone);
            green = random.nextInt(verifyCode.maxTone);
            blue = random.nextInt(verifyCode.maxTone);
            gd.setColor(new Color(red, green, blue));
            gd.drawString(String.valueOf(code.charAt(i)), (i + 1) * codeX, codeY);
        }
        return buffImg;
    }

    @Override
    public int getCodeCount() {
        return verifyCode.codeCount;
    }

    public static void validateCode(HttpServletRequest httpServletRequest) throws AuthenticationException {
        HttpSession session = httpServletRequest.getSession();
        // 获取 session 中的验证码
        String codeInSession = (String) session.getAttribute(LoginController.SESSION_KEY_VERIFY_CODE);
        Long codeTime = (Long) session.getAttribute(LoginController.SESSION_KEY_VERIFY_CODE_TIME);
        // 获取请求中的验证码
        String codeInRequest = httpServletRequest.getParameter(CAPTCHA);

        if (StringUtils.isEmpty(codeInRequest)) {
            throw new ValidateCaptchaException(CAPTCHA_IS_EMPTY);
        }
        if (codeInSession == null) {
            throw new ValidateCaptchaException(CAPTCHA_NOT_EXISTED);
        }
        boolean isExpire = (System.currentTimeMillis() - codeTime) > 60000;
        System.out.println(codeTime);
        if (isExpire) {
            removeCode(session);
            throw new ValidateCaptchaException(CAPTCHA_IS_EXPIRED);
        }
        if (!codeInSession.equalsIgnoreCase(codeInRequest)) {
            throw new ValidateCaptchaException(CAPTCHA_NOT_MATCHED);
        }

        removeCode(session);
    }

    private static void removeCode(HttpSession session) {
        session.removeAttribute(LoginController.SESSION_KEY_VERIFY_CODE);
        session.removeAttribute(LoginController.SESSION_KEY_VERIFY_CODE_TIME);
    }

}
