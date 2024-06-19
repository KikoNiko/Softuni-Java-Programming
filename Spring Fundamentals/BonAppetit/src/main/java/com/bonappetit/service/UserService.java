package com.bonappetit.service;

import com.bonappetit.model.dto.UserLoginDTO;
import com.bonappetit.model.dto.UserRegisterDTO;
import com.bonappetit.model.entity.Recipe;

import java.util.List;

public interface UserService {
    void register(UserRegisterDTO userRegisterDTO);

    boolean login(UserLoginDTO userLoginDTO);

    void logout();

    boolean checkCredentials(String username, String password);

    boolean isLoggedUser();

    List<Recipe> getFavouriteRecipes(long userId);
}
