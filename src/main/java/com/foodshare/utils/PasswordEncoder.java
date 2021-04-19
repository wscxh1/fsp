package com.foodshare.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder{
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encode(CharSequence charSequence) {
        return encoder.encode(charSequence);
    }

    public static boolean matches(CharSequence charSequence, String s) {
        return encoder.matches(charSequence, s);
    }
}
