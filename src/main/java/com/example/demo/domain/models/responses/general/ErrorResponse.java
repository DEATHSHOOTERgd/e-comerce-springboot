package com.example.demo.domain.models.responses.general;

import lombok.Getter;
import lombok.Setter;

public class ErrorResponse extends BaseResponse{
    @Getter
    @Setter
    private CustomError error;

    public ErrorResponse(CustomError error, int code, String message, String traceId){
        super(code, message, traceId);
        this.error = error;
    }
}
