package com.example.demo.domain.models.exceptions;

import lombok.Getter;
import lombok.Setter;

public abstract class CustomException extends RuntimeException{
    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String errorMessage;
}
