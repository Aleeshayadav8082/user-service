package com.maveric.userservice.controller;

import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> getAllUser(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                     @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize){
        return userService.getAllUser(pageNumber, pageSize);
    }
}
