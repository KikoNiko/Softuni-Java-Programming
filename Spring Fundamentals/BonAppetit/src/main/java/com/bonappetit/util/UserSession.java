package com.bonappetit.util;

import com.bonappetit.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserSession {

    private long id;

    private String username;

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void loginUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public boolean isLoggedIn() {
        return this.id != 0;
    }

    public void logout() {
        this.id = 0;
        this.username = "";
    }
}
