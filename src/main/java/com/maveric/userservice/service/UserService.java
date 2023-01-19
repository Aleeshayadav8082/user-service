package com.maveric.userservice.service;

import com.maveric.userservice.dto.UserDto;

public interface UserService {
    UserDto updateUser(UserDto userDto, long userId);

    UserDto createUserDetails(UserDto userDto) ;
}
