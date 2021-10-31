package com.okava.pay.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class Utility {

    public static Pageable from(int page, int limit){
        return PageRequest.of(page, limit, Sort.Direction.ASC, "id");
    }
}
