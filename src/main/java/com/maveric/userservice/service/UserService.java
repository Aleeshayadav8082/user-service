package com.maveric.userservice.service;

import com.maveric.userservice.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUser(int pageNumber, int pageSize);
public interface UserService {
    UserDto getUserByEmail(String email);
 UserDto getUserById(long id);
    UserDto updateUser(UserDto userDto, long userId);
    void deleteUser(long id);

    UserDto createUserDetails(UserDto userDto) ;
}
