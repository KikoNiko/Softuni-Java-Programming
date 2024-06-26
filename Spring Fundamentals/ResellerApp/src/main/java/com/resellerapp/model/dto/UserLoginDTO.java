package com.resellerapp.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class UserLoginDTO {

    @Length(min = 3, max = 20)
    @NotBlank
    private String username;

    @Length(min = 3, max = 20)
    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
