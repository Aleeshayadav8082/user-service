package com.maveric.userservice.controller;

import com.maveric.userservice.model.User;
import com.maveric.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @PutMapping("/users/{userid}")
    public ResponseEntity<User> updateUser(@PathVariable("userid") long userId, @RequestBody User user) {
        return new ResponseEntity<User> (userService.updateuser(userId,user), HttpStatus.OK);
    }
}
