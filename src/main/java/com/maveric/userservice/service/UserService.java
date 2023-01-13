package com.maveric.userservice.service;

import com.maveric.userservice.dto.UserDto;

public interface UserService {
    UserDto getUserByEmail(String email);
}
