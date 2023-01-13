package com.maveric.userservice.service;

import com.maveric.userservice.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUser(int pageNumber, int pageSize);
}
