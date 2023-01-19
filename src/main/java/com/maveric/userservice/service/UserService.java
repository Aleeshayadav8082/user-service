package com.maveric.userservice.service;

import com.maveric.userservice.dto.UserDto;

public interface UserService {
    UserDto updateUser(UserDto userDto, long userId);
    void deleteUser(long id);

    UserDto createUserDetails(UserDto userDto) ;
}
