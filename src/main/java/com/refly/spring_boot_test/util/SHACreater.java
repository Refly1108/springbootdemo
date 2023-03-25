package com.refly.spring_boot_test.util;

import com.refly.spring_boot_test.service.impl.WeixinServiceImpl;

import java.security.MessageDigest;

public class SHACreater {


    public static String getSha1(String str) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
    public static void main(String[] args) {

        WeixinServiceImpl weixinService = new WeixinServiceImpl();
        weixinService.getSignature("1679751805");
//        System.out.println(generateSignatureSHA1("67_kkg-p04-OTSYQyDM2Eu-9AlCsoJi51TnbhVobztt7Nd4KefKBLM5jIvgYj5Lqskfl6akGGqNi7km72wygAr3yE91ikC7nVqaaLzoTPXKn1j3RhQ6xVGjJPZEdKgEHOjAIALOV",
//                "1679751805"));
    }

    public static String generateSignatureSHA1(String signature,String timestamp) {

        System.out.println(signature);
        String str = "jsapi_ticket=" + signature + "&noncestr=const&timestamp=" + timestamp + "&url=http://localhost:3000/";
        return getSha1(str);

    }
}
