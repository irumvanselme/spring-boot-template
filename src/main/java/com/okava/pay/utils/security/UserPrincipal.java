package com.okava.pay.utils.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.okava.pay.models.User;
import com.okava.pay.models.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    private UUID id;

    private String fullNames;

    private String email;

    private String nationalId;

    private String phoneNumber;

    private String location;

    private String profileImage;

    @JsonIgnore
    private String password;

    private ERole role;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities;

        authorities = Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString()));

        return new UserPrincipal(
                user.getId(),
                user.getFullNames(),
                user.getEmail(),
                user.getNationalId(),
                user.getPhoneNumber(),
                user.getLocation(),
                user.getProfileImage(),
                user.getPassword(),
                user.getRole(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
