package com.likebookapp.util;

import com.likebookapp.model.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession {

    private long id;
    private String username;

    public boolean isLoggedIn() {
        return this.id != 0;
    }

    public void loginUser(User user) {
        id = user.getId();
        username = user.getUsername();
    }

    public void logout() {
        id = 0;
        username = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
