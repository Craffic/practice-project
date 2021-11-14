package com.craffic.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtil {

    public static String getBCryptPasswordStr(String plaint){
        String encodePwd = new BCryptPasswordEncoder().encode(plaint);
        System.out.println(encodePwd);
        return encodePwd;
    }


    public static void main(String[] args) {
        String bCryptPasswordStr = getBCryptPasswordStr("123");
    }
}
