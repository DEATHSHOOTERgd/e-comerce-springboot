package com.example.demo.domain.models.requests.auth;

import lombok.Getter;
import lombok.Setter;

public class RegisterRequest {
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private String email;
}
