package com.example.demo.domain.models.exceptions;

public class AuthException extends CustomException{
    public AuthException(String message){
        this.setErrorMessage(message.isEmpty()?"Bad request":message);
        this.setCode(401);
    }
}
