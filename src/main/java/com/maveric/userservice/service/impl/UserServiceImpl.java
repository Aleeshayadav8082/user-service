package com.maveric.userservice.service.impl;

import com.maveric.userservice.converter.DtoToModelConverter;
import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.exception.EmailDuplicateException;
import com.maveric.userservice.exception.UserNotFoundException;
import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import com.maveric.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;


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
    public UserDto createUser(UserDto userDto) {
        User user = dtoToModelConverter.dtoToUserCreate(userDto);
        Optional<User> existingUser = userRepository.findUserByEmail(user.getEmail());
        if (existingUser.isPresent()) throw new EmailDuplicateException(
                "User with email " + userDto.getEmail() + " already exist");
        User savedUser = userRepository.save(user);
        return dtoToModelConverter.userToDtoCreate(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not Found with id " + userId));
        if(userRepository.findUserByEmail(userDto.getEmail()).isPresent()){
            throw new EmailDuplicateException("User with email " + userDto.getEmail() + " already exist");
        }

        existingUser.setFirstName(userDto.getFirstName());
        existingUser.setLastName(userDto.getLastName());
        existingUser.setMiddleName(userDto.getMiddleName());
        existingUser.setAddress(userDto.getAddress());
        existingUser.setGender(userDto.getGender());
        existingUser.setDateOfBirth(userDto.getDateOfBirth());
        existingUser.setPhoneNumber(userDto.getPhoneNumber());
        existingUser.setEmail(userDto.getEmail());

        User updatedUser = userRepository.save(existingUser);
        UserDto userDto1 = dtoToModelConverter.userToDtoUpdate(updatedUser);
        return userDto1;
    }

    @Override
    public List<UserDto> getAllUser(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> userPage = userRepository.findAll(pageable);

        List<User> users = userPage.getContent();
        List<UserDto> userDtos = users.stream().map(user -> dtoToModelConverter.userToDtoUpdate(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public UserDto getUserById(long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found with id " + id));
        return dtoToModelConverter.userToDtoUpdate(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(
                ()-> new EmailDuplicateException("User not found with email " + email));
        return dtoToModelConverter.userToDtoEmail(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.findById(id).orElseThrow(
                ()->new UserNotFoundException("User not found with id " + id));

        userRepository.deleteById(id);
    }
}


