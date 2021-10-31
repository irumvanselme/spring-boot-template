package com.okava.pay.utils.dtos;

import com.okava.pay.utils.security.ValidPassword;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class RegisterDTO {

    @NotEmpty
    private String fullNames;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    @ValidPassword
    private String password;
}
