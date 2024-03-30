package bg.softuni.gamestore.service;


import bg.softuni.gamestore.data.entities.User;
import bg.softuni.gamestore.service.dtos.UserLoginDTO;
import bg.softuni.gamestore.service.dtos.UserRegisterDTO;

import java.util.Optional;

public interface UserService {

    String regiserUser(UserRegisterDTO userRegisterDTO);

    String loginUser(UserLoginDTO userLoginDTO);

    String logout();

    Optional<User> getLoggedIn();
}
