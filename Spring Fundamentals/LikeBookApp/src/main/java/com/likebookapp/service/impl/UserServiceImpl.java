package com.likebookapp.service.impl;

import com.likebookapp.model.dto.UserLoginDTO;
import com.likebookapp.model.dto.UserRegisterDTO;
import com.likebookapp.model.entity.User;
import com.likebookapp.repository.UserRepository;
import com.likebookapp.service.UserService;
import com.likebookapp.util.UserSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserSession userSession;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserSession userSession, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        Optional<User> byUsernameOrEmail =
                userRepository.findUserByUsernameOrEmail(userRegisterDTO.getUsername(), userRegisterDTO.getEmail());
        if (byUsernameOrEmail.isPresent()) {
            return;
        }
        User user =  new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void login(UserLoginDTO userLoginDTO) {
        Optional<User> optionalUser = userRepository.findUserByUsername(userLoginDTO.getUsername());
        if (optionalUser.isEmpty()) {
            return;
        }
        userSession.loginUser(optionalUser.get());
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        Optional<User> byUsername = userRepository.findUserByUsername(username);
        return byUsername.filter(user -> passwordEncoder.matches(password, user.getPassword())).isPresent();
    }

    @Override
    public void logout() {
        userSession.logout();
    }

    @Override
    public boolean isUserLoggedIn() {
        return userSession.isLoggedIn();
    }

}
