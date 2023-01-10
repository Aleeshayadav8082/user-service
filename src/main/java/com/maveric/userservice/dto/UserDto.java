package com.maveric.userservice.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

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
    private String password;
    private String gender;

}
