package com.maveric.userservice.exception;

public class EmailDuplicateException extends RuntimeException{
    public EmailDuplicateException(String message){
        super(message);
    }
}
