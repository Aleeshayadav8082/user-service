package com.maveric.userservice.service;

import com.maveric.userservice.dto.UserDto;

public interface UserService {
    void deleteUser(long id);

    UserDto createUserDetails(UserDto userDto) ;
}
