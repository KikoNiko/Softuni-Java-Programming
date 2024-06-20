package com.resellerapp.service;

import com.resellerapp.model.dto.UserLoginDTO;
import com.resellerapp.model.dto.UserRegisterDTO;

public interface UserService {

    void register(UserRegisterDTO userRegisterDTO);

    void login(UserLoginDTO userLoginDTO);

    void logout();

    boolean checkCredentials(String username, String password);
}
