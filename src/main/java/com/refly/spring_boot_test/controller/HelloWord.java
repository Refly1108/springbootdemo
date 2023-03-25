package com.refly.spring_boot_test.controller;

import com.refly.spring_boot_test.model.SignatureResponse;
import com.refly.spring_boot_test.service.WeixinService;
import com.refly.spring_boot_test.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class HelloWord {

    @Autowired
    private WishService wishService;

    @Autowired
    private WeixinService weixinService;
    @RequestMapping("/helloWord")
    @ResponseBody
    public String helloWord(String name){
        System.out.println("helloWord"+name);
        return "hello word!"+name;
    }

    @RequestMapping("/getWish")
    @ResponseBody
    public ResponseEntity getWish(){
        System.out.println("helloWord");
        return wishService.getWish();
    }

    @RequestMapping("/getSignature")
    @ResponseBody
    public SignatureResponse getSignature(String timeStap){
        System.out.println(timeStap);
        return weixinService.getSignature(timeStap);
    }
}
