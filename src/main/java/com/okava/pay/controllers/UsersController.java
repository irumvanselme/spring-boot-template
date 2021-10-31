package com.okava.pay.controllers;

import com.okava.pay.models.enums.ERole;
import com.okava.pay.services.IUserService;
import com.okava.pay.utils.Constants;
import com.okava.pay.utils.Formatter;
import com.okava.pay.utils.Utility;
import com.okava.pay.utils.payload.ApiResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UsersController {

    private final IUserService userService;

    public UsersController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> all(@RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit){
        Pageable pageable = Utility.from(page, limit);

        return Formatter.ok(userService.all(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> byId(@PathVariable UUID id){

        return Formatter.ok(userService.findById(id));
    }

    @GetMapping("/by-role/{role}")
    public ResponseEntity<ApiResponse> byRole(@RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page, @RequestParam(value = "limit", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit, @PathVariable ERole role){
        Pageable pageable = Utility.from(page, limit);

        return Formatter.ok(userService.byRole(role, pageable));
    }
}
