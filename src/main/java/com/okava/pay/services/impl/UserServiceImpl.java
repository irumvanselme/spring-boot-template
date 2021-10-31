package com.okava.pay.services.impl;

import com.okava.pay.utils.Utility;
import com.okava.pay.utils.dtos.RegisterDTO;
import com.okava.pay.utils.exceptions.BadRequestException;
import com.okava.pay.utils.exceptions.ResourceNotFoundException;
import com.okava.pay.models.User;
import com.okava.pay.models.enums.ERole;
import com.okava.pay.repositories.IUserRepository;
import com.okava.pay.services.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> all(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id.toString()));
    }

    @Override
    public Page<User> byRole(ERole role, Pageable pageable) {
        return userRepository.findByRole(role, pageable);
    }

    @Override
    public User create(RegisterDTO dto) {
        User user = new User();

        user.setRole(ERole.USER);
        user.setEmail(dto.getEmail());
        user.setFullNames(dto.getFullNames());
        user.setPassword(Utility.encode(dto.getPassword()));

        if (!isUnique(user))
            throw new BadRequestException("The provided email is already used in the app");

        return userRepository.save(user);
    }

    @Override
    public boolean isUnique(User user) {
        Optional<User> userOptional = this.userRepository.findByEmailOrPhoneNumberOrNationalId(user.getEmail(), user.getPhoneNumber(), user.getNationalId());
        return userOptional.isEmpty();
    }
}
