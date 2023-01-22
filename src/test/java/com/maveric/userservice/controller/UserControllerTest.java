package com.maveric.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.userservice.constant.Gender;
import com.maveric.userservice.dto.UserDto;
import com.maveric.userservice.exception.EmailDuplicateException;
import com.maveric.userservice.exception.UserNotFoundException;
import com.maveric.userservice.model.User;
import com.maveric.userservice.repository.UserRepository;
import com.maveric.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)

class UserControllerTest {


    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String API_V1_USERS = "/api/v1/users";
    private static final String API_V1_USERS_EMAIL = "/api/v1/users/getUserByEmail";


    @Test
    void createUser() throws Exception{
        when(userService.createUser(any(UserDto.class))).thenReturn(getUserDto());
        mockMvc.perform(post(API_V1_USERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getUser()))).andExpect(status().isCreated())
                .andDo(print());
    }


    @Test
    void shouldThrowErrorWhenCreateUserDetailsAreWrong() throws Exception{
        UserDto user = new UserDto();
        user.setId(1l);
        user.setFirstName(null);
        user.setMiddleName("");
        user.setLastName("Yadav");
        user.setAddress("Pune");
        user.setGender(Gender.FEMALE);
        user.setEmail("aleeshay@maveric-systems.com");
        user.setPassword("Pass@word1");
        user.setDateOfBirth(Date.from(Instant.parse("1994-10-22T00:00:00Z")));
        user.setPhoneNumber("8875401044");

        mockMvc.perform(post(API_V1_USERS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andDo(print());

    }
    @Test
    void getUserById() throws Exception{
        when(userService.getUserById(anyLong())).thenReturn(getUserDto());
        mockMvc.perform(get(API_V1_USERS + "/" + 1L))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    void shouldReturnErrorWhenWrongUserIdForGetUserByID() throws Exception{
        when(userService.getUserById(anyLong())).thenThrow(new UserNotFoundException("User Not found"));
        mockMvc.perform(get(API_V1_USERS+"/" + 2L))
                .andExpect(status().isNotFound())
                .andDo(print());

    }


    @Test
    void getUserByEmail() throws Exception{
        when(userService.getUserByEmail(any())).thenReturn(getUserDto());
        mockMvc.perform(get(API_V1_USERS_EMAIL + "/aleeshay@maveric-systems.com"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldReturnErrorWhenWWrongEmailForGetUserByEmail() throws Exception{
        when(userService.getUserByEmail(anyString())).thenThrow(new EmailDuplicateException("User not found with email id"));
        mockMvc.perform(get(API_V1_USERS_EMAIL+"/aleeshayadav@gmail.com"))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }


    @Test
    void updateUser() throws Exception {
        when(userService.updateUser(any(UserDto.class), anyLong())).thenReturn(getUserDto());
        mockMvc.perform(put(API_V1_USERS + "/" +1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getUser()))).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void shouldThrowErrorWhenUpdateUserDetailsAreWrong() throws Exception{
        UserDto user = new UserDto();
        user.setId(1l);
        user.setFirstName(null);
        user.setMiddleName("");
        user.setLastName("Yadav");
        user.setAddress("Pune");
        user.setGender(Gender.FEMALE);
        user.setEmail("aleeshay@maveric-systems.com");
        user.setDateOfBirth(Date.from(Instant.parse("1994-10-22T00:00:00Z")));
        user.setPhoneNumber("8875401044");

        mockMvc.perform(put(API_V1_USERS + "/" +1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }



    @Test
    void getAllUsers() throws Exception {
        when(userService.getAllUser(anyInt(), anyInt())).thenReturn(Arrays.asList(getUserDto(), getUserDto()));
        mockMvc.perform(get(API_V1_USERS + "?page=0&pageSize=2"))
                .andExpect(status().isOk())
                .andDo(print());
    }




    @Test
    void deleteUser() throws Exception{
        mockMvc.perform(delete(API_V1_USERS + "/" + 1L))
                .andExpect(status().isOk())
                .andDo(print());
    }

    public static User getUser() {
        User user = new User();
        user.setId(1l);
        user.setFirstName("Aleesha");
        user.setMiddleName("");
        user.setLastName("Yadav");
        user.setAddress("Pune");
        user.setGender(Gender.FEMALE);
        user.setEmail("aleehsay@maveric-systems.com");
        user.setPassword("Pass@word1");
        user.setDateOfBirth(Date.from(Instant.parse("1994-10-22T00:00:00Z")));
        user.setPhoneNumber("8875401044");
        return user;

    }

    public static UserDto getUserDto() {
        UserDto user = new UserDto();
        user.setId(1l);
        user.setFirstName("Aleesha");
        user.setMiddleName("");
        user.setLastName("Yadav");
        user.setAddress("Pune");
        user.setGender(Gender.FEMALE);
        user.setEmail("aleeshay@maveric-systems.com");
        user.setPassword("Pass@word1");
        user.setDateOfBirth(Date.from(Instant.parse("1994-10-22T00:00:00Z")));
        user.setPhoneNumber("8875401044");
        return user;
    }
}
