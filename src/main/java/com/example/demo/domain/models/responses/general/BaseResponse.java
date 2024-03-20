package com.example.demo.domain.models.responses.general;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseResponse {
    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private String traceId;
}
