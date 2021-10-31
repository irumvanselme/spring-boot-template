package com.okava.pay.repositories;

import com.okava.pay.models.User;
import com.okava.pay.models.enums.ERole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {

    Page<User> findByRole(ERole role, Pageable pageable);
}
