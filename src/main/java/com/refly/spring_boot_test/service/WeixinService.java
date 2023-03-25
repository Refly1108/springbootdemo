package com.refly.spring_boot_test.service;

import com.refly.spring_boot_test.model.SignatureResponse;
import org.springframework.http.ResponseEntity;

public interface WeixinService {

    public SignatureResponse getSignature(String timeStap);
}
