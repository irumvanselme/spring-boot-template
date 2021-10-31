package com.okava.pay.models;

import com.okava.pay.audits.Model;
import com.okava.pay.models.enums.ERole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "full_names")
    private String fullNames;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "national_id", unique = true)
    private String nationalId;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name = "location")
    private String location;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private ERole role;
}
