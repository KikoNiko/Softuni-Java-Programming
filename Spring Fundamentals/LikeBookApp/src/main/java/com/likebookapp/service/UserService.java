package com.likebookapp.service;

import com.likebookapp.model.dto.UserLoginDTO;
import com.likebookapp.model.dto.UserRegisterDTO;

public interface UserService {

    void register(UserRegisterDTO userRegisterDTO);

    void login(UserLoginDTO userLoginDTO);

    boolean checkCredentials(String username, String password);

    void logout();

    boolean isUserLoggedIn();

    long getLoggedUserId();
}
