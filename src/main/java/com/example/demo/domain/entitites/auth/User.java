package com.example.demo.domain.entitites.auth;

import com.example.demo.domain.entitites.general.Base;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Table("users")
public class User extends Base {
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
    @Column("last_name")
    private String lastName;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    @Column("last_login")
    private LocalDateTime lastLogin;
    @Getter
    @Setter
    private boolean status;
}
