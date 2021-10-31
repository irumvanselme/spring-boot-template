package com.okava.pay.services;

import com.okava.pay.models.User;
import com.okava.pay.models.enums.ERole;
import com.okava.pay.utils.dtos.RegisterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IUserService {

    Page<User> all(Pageable pageable);

    User findById(UUID id);

    Page<User> byRole(ERole role, Pageable pageable);

    User create(RegisterDTO dto);

    boolean isUnique(User user);
}
