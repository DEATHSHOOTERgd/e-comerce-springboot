package com.example.demo.application.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtils {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean validatePassword(String password, String hashedPassword) {
        return bCryptPasswordEncoder.matches(password, hashedPassword);
    }

}
