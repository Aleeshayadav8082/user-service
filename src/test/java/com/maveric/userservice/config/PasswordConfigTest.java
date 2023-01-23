package com.maveric.userservice.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PasswordConfigTest {
    @InjectMocks
    private PasswordConfig passwordConfig;


    @Test
    void passwordEncoder() {
            BCryptPasswordEncoder bCryptPasswordEncoder = passwordConfig.passwordEncoder();
            assertNotNull(bCryptPasswordEncoder);
    }
}