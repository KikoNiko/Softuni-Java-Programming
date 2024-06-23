package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.PaintingDisplayDTO;
import com.paintingscollectors.model.dto.UserLoginDTO;
import com.paintingscollectors.model.dto.UserRegisterDTO;

import java.util.List;
import java.util.Set;

public interface UserService {
    void register(UserRegisterDTO userRegisterDTO);

    boolean login(UserLoginDTO userLoginDTO);

    void logout();

    boolean checkCredentials(String username, String password);

    boolean isLoggedUser();

    long getLoggedUserId();

    Set<PaintingDisplayDTO> getAllFavoritePaintings(long userId);

}
