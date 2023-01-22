package com.maveric.userservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.maveric.userservice.constant.Gender;
import com.maveric.userservice.constraints.BirthDateValidator;
import com.maveric.userservice.constraints.GenderValidator;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private Long id;

    @NotEmpty(message = "First name required")
    private String firstName;
    private String middleName;

    @NotEmpty(message = "Last name required")
    private String lastName;

    @Email(regexp = "[a-z0-9._%+-]+@[a-zA-Z]+-[a-zA-Z]+\\.[a-zA-Z]+",
            message = "Invalid Email ID")
    @NotEmpty(message = "Email cannot be blank")
    private String email;

    @NotEmpty
    @Size(min = 10, max = 10, message = "Phone Number required")
    private String phoneNumber;

    @NotEmpty(message = "Address required")
    private String address;

    @NotNull
    @BirthDateValidator(message = "User not of appropriate age (18+ users only) ")
    @Past(message = "Invalid Date of Birth")
    private Date dateOfBirth;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain at least one upper case (A-Z), one lower case (a-z), one number (0-9) and one special character (@!#$ etc)")
    private String password;

    @Enumerated(EnumType.STRING)
    @GenderValidator(anyOfTheseGender = {Gender.MALE, Gender.FEMALE})
    private Gender gender;
}














