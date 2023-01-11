package com.maveric.userservice.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(long id){
        super("Could not found user " + id);
    }

}
