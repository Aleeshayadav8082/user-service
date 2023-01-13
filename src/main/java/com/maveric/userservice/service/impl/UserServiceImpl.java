package com.maveric.userservice.service.impl;

import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import com.maveric.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
       super();
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser(Integer pageNumber, Integer pageSize) {


        Pageable p = PageRequest.of(pageNumber,pageSize);
        Page<User> pagePost =  userRepository.findAll(p);
        List<User> allUsers = pagePost.getContent();
        return  allUsers;
    }
}


