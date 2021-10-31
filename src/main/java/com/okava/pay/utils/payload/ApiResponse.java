package com.okava.pay.utils.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

    boolean success;

    String message;

    Object data;

    public static ApiResponse success(String message) {
        return new ApiResponse(true, message, null);
    }

    public static ApiResponse success(Object data) {
        return new ApiResponse(true, null, data);
    }
}
