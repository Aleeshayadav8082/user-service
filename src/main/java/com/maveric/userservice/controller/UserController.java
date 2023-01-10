package com.maveric.userservice.controller;

import com.maveric.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1")
@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") long id) {
        userService.deleteUser(id);
        return  new ResponseEntity<String>("User has been deleted", HttpStatus.OK);
    }
}
