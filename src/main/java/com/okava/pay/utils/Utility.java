package com.okava.pay.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utility {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static Pageable from(int page, int limit){
        return PageRequest.of(page, limit, Sort.Direction.ASC, "id");
    }

    public static String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
