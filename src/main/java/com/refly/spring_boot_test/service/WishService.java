package com.refly.spring_boot_test.service;

import com.refly.spring_boot_test.model.SetWishResponse;
import org.springframework.http.ResponseEntity;

public interface WishService {

    public ResponseEntity getWish();
}
