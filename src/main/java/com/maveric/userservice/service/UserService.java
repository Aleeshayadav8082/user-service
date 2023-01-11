package com.maveric.userservice.service;

import com.maveric.userservice.model.User;

public interface UserService {
    User updateuser(long userId, User user);
}
