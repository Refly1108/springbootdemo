package com.refly.spring_boot_test.service.impl;

import com.refly.spring_boot_test.model.SetWishResponse;
import com.refly.spring_boot_test.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class WishServiceImpl implements WishService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public ResponseEntity getWish() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "text/plain");
        String url = "http://175.178.13.221/service/getWish?printerId=1";
        HttpEntity<String> httpEntity = new HttpEntity((MultiValueMap)httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET,httpEntity,String.class);

        System.out.println(responseEntity.toString());
        return responseEntity;
    }
}
