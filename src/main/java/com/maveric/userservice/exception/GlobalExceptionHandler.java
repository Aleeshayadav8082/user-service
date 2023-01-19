package com.maveric.userservice.exception;

import com.maveric.userservice.constant.MessageConstant;
import com.maveric.userservice.dto.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleMethodArgsNotValidException(MethodArgumentNotValidException ex) {
        Error error = getError(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                String.valueOf(HttpStatus.BAD_REQUEST));

        return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmailDuplicateException.class)
    public ResponseEntity<Error> handleEmailDuplication(EmailDuplicateException e) {
        Error error = getError(e.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()));

        return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleFormatException(HttpMessageNotReadableException e) {
        Error error = getError(MessageConstant.GENDER_ERROR, String.valueOf(HttpStatus.BAD_REQUEST));

        return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
    }


    private Error getError(String message , String code){
        Error error = new Error();
        error.setCode(code);
        error.setMessage(message);
        return error;
    }
}