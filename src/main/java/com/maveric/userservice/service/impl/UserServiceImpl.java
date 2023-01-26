package com.maveric.userservice.service.impl;

import com.maveric.userservice.converter.DtoToModelConverter;
import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.dto.UserEmailDto;
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

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DtoToModelConverter dtoToModelConverter;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = dtoToModelConverter.dtoToUserCreate(userDto);
        Optional<User> existingUser = userRepository.findUserByEmail(user.getEmail());
        if (!existingUser.isPresent()) {

            User savedUser = userRepository.save(user);
            return dtoToModelConverter.userToDtoCreate(savedUser);

        } else {
            throw new EmailDuplicateException(
                    "User with email " + userDto.getEmail() + " already exist");
        }
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
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
    public List<UserDto> getAllUsers(int page, int pageSize) {

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<User> userPage = userRepository.findAll(pageable);

        List<User> users = userPage.getContent();
        return users.stream().map(user -> dtoToModelConverter.userToDtoUpdate(user)).toList();
    }

    @Override
    public UserDto getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found with id " + id));
        return dtoToModelConverter.userToDtoUpdate(user);
    }

    @Override
    public UserEmailDto getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElseThrow(
                ()-> new EmailDuplicateException("User not found with email " + email));
        return dtoToModelConverter.userToDtoEmail(user);
    }

    @Override
    public void deleteUser(String id) {
        if (userRepository.findById(id).isPresent()){
            userRepository.deleteById(id);
        }
        else {
            throw new UserNotFoundException("User not found with id " + id);
        }
    }
}