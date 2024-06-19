package com.bonappetit.service;

import com.bonappetit.model.dto.UserLoginDTO;
import com.bonappetit.model.dto.UserRegisterDTO;

public interface UserService {
    void register(UserRegisterDTO userRegisterDTO);

    boolean login(UserLoginDTO userLoginDTO);

    boolean checkCredentials(String username, String password);
}
