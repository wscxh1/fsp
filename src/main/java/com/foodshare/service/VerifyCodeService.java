package com.foodshare.service;

import java.awt.image.BufferedImage;

public interface VerifyCodeService {
    public String getCode(int length);

    public BufferedImage getImage(String code);

    public int getCodeCount();
}
