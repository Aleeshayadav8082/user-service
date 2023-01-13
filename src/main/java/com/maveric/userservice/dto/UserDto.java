package com.maveric.userservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

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
