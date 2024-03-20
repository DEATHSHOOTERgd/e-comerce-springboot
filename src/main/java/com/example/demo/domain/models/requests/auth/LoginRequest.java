package com.example.demo.domain.models.requests.auth;

import lombok.Getter;
import lombok.Setter;

public class LoginRequest {
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
}
