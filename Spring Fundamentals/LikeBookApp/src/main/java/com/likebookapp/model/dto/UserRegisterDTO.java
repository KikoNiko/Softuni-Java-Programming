package com.likebookapp.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegisterDTO {
    @Size(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    @NotEmpty
    private String username;
    @Email
    @NotEmpty(message = "Email cannot be empty!")
    private String email;
    @Size(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    @NotEmpty
    private String password;
    @NotEmpty
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
