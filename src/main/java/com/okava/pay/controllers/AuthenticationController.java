package com.okava.pay.controllers;

import com.okava.pay.services.IUserService;
import com.okava.pay.utils.Formatter;
import com.okava.pay.utils.dtos.RegisterDTO;
import com.okava.pay.utils.payload.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final IUserService userService;

    public AuthenticationController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(){
        return Formatter.done();
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@Valid @RequestBody RegisterDTO dto){
        return Formatter.ok(userService.create(dto));
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse> profile(){
        return Formatter.done();
    }
}
