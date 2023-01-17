package com.maveric.userservice.model;

import com.maveric.userservice.constant.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name= "user")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private Date dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String password;

    @Column(updatable = false)
    private Date createdAt = new Date();

    private Date updatedAt = new Date();
}
