package com.dictionaryapp.model.dto;

import jakarta.validation.constraints.*;

public class UserRegisterDTO {
    @Size(min = 3, max = 20)
    @NotBlank
    private String username;
    @NotEmpty
    @Email
    private String email;
    @Size(min = 3, max = 20)
    @NotBlank
    private String password;
    @Size(min = 3, max = 20)
    @NotBlank
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
