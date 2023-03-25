package com.refly.spring_boot_test.config;

import org.springframework.context.annotation.Bean;

import java.util.Date;


public class WeiXinConfig {

    public static String Token_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=";
   //                                      https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi
    public static String API_Ticket_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=";
    public static String appid  = "wxa4065fe099206a50";
    public static String secret  = "468b71d17fea3019082a739ef388c13a";
    public static String grant_type   = "client_credential";
    public static String token = null;
    public static Date tokenCreateTime = null;

    public static Date getTokenCreateTime() {
        return tokenCreateTime;
    }

    public static void setTokenCreateTime(Date tokenCreateTime) {
        WeiXinConfig.tokenCreateTime = tokenCreateTime;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        WeiXinConfig.token = token;
    }
    public static boolean signatureChecking() {

       // System.out.println(day2.getTime() / 1000-1679760380);
        if(tokenCreateTime!=null&&((new Date().getTime()-tokenCreateTime.getTime())/1000)<7200){

            return true;
        }
        return false;

    }
}
