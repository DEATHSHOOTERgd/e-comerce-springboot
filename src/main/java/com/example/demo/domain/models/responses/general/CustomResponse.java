package com.example.demo.domain.models.responses.general;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

public class CustomResponse<T> extends BaseResponse {
    @Getter
    @Setter
    private T data;

    public CustomResponse(T data, int code, String message, String traceId){
        super(code, message, traceId);
        this.data = data;
    }
}
