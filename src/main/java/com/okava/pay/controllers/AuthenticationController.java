package com.okava.pay.controllers;

import com.okava.pay.services.IUserService;
import com.okava.pay.utils.Formatter;
import com.okava.pay.utils.dtos.LoginDTO;
import com.okava.pay.utils.dtos.RegisterDTO;
import com.okava.pay.utils.payload.ApiResponse;
import com.okava.pay.utils.payload.JwtAuthenticationResponse;
import com.okava.pay.utils.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final IUserService userService;


    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthenticationController(IUserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@Valid @RequestBody LoginDTO dto){

        String jwt = null;

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getLogin(), dto.getPassword()));

        try {
            SecurityContextHolder.getContext().setAuthentication(authentication);

            jwt = jwtTokenProvider.generateToken(authentication);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return ResponseEntity.accepted().body(new JwtAuthenticationResponse(jwt));
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
