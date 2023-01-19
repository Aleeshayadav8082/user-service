package com.maveric.userservice.service;

import com.maveric.userservice.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto, long userId);
    List<UserDto> getAllUser(int pageNumber, int pageSize);
    UserDto getUserById(long id);
    UserDto getUserByEmail(String email);


    void deleteUser(long id);


}
