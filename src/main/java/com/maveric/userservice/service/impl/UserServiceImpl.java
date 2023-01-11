package com.maveric.userservice.service.impl;

import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import com.maveric.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
       super();
        this.userRepository = userRepository;
    }

    @Override
    public User updateuser(long userId, User user) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                ()->new RuntimeException("User not found"));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setMiddleName(user.getMiddleName());
        existingUser.setAddress(user.getAddress());
        existingUser.setGender(user.getGender());
        existingUser.setDateOfBirth(user.getDateOfBirth());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setEmail(user.getEmail());

        userRepository.save(existingUser);
        return existingUser;

    }
}


