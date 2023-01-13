package com.maveric.userservice.controller;

import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/users/getUserByEmail/{emailId}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable("emailId") String email){
        return new ResponseEntity<UserDto>(userService.getUserByEmail(email), HttpStatus.OK);
    }
}
