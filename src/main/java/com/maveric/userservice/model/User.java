package com.maveric.userservice.model;

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

    //@Column(nullable = false)
    private String firstName;


    private String middleName;

    //@Column(nullable = false)
    private String lastName;

    //@Column(nullable = false)
    private String email;

    //@Column(nullable = false)
    private String phoneNumber;

    //@Column(nullable = false)
    private String address;

    private Date dateOfBirth;


    private String gender;

    //@Column(nullable = false)
    private String password;

    private Date createdAt = new Date();

    private Date updatedAt = new Date();
}
