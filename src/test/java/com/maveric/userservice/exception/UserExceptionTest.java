package com.maveric.userservice.exception;

import com.maveric.userservice.dto.Error;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserExceptionTest {

    @InjectMocks
    GlobalExceptionHandler globalExceptionHandler;

    @Test
    void emailDuplicateException() {
        EmailDuplicateException exception = new EmailDuplicateException("User not found with email");
        ResponseEntity<Error> error = globalExceptionHandler.handleEmailDuplication(exception);
        assertEquals("400", error.getBody().getCode());
    }

    @Test
    void userNotFoundException() {
        UserNotFoundException exception = new UserNotFoundException("User Not found");
        ResponseEntity<Error> error = globalExceptionHandler.handleUserNotFound(exception);
        assertEquals("404", error.getBody().getCode());
    }
}