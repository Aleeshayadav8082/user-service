package com.maveric.userservice.controller;

import com.maveric.userservice.model.User;
import com.maveric.userservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUser();
    }
}
