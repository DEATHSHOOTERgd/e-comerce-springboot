package com.example.demo.domain.models.exceptions;

public class ServerFaultException extends CustomException{
    public ServerFaultException(String message){
        this.setErrorMessage(message.isEmpty()?"Error interno.":message);
        this.setCode(500);
    }

    public ServerFaultException(String message, int code){
        this.setErrorMessage(message.isEmpty()?"Error interno.":message);
        this.setCode(code);
    }
}
