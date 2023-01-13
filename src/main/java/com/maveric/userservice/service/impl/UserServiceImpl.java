package com.maveric.userservice.service.impl;

import com.maveric.userservice.converter.DtoToModelConverter;
import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import com.maveric.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    private DtoToModelConverter dtoToModelConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public UserDto createUserDetails(UserDto userDto) {
        User user = dtoToModelConverter.dtoToUserCreate(userDto);
        User savedUser = userRepository.save(user);
        return dtoToModelConverter.userToDtoCreate(savedUser);
    }
}