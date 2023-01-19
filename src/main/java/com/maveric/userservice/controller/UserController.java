package com.maveric.userservice.controller;

import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUserDetails(@Valid @RequestBody UserDto userDto) {
        userDto.setPassword(this.bCryptPasswordEncoder.encode(userDto.getPassword()));
        return new ResponseEntity<UserDto>(userService.createUserDetails(userDto), HttpStatus.CREATED);
    }
}
