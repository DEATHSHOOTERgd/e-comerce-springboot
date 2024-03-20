package com.example.demo.domain.models.exceptions;

public class ClientFaultException extends CustomException{
    public ClientFaultException(String message){
        this.setErrorMessage(message.isEmpty()?"Bad request":message);
        this.setCode(400);
    }

    public ClientFaultException(String message, int code){
        this.setErrorMessage(message.isEmpty()?"Bad request":message);
        this.setCode(code);
    }
}
