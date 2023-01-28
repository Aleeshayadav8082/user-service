package com.maveric.userservice.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

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