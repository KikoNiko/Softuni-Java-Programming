package com.plannerapp.service;

import com.plannerapp.model.dto.UserLoginDTO;
import com.plannerapp.model.dto.UserRegisterDTO;

public interface UserService {

    void register(UserRegisterDTO userRegisterDTO);

    void login(UserLoginDTO userLoginDTO);

    void logout();

    boolean checkCredentials(String username, String password);

    boolean isLoggedUser();
}
