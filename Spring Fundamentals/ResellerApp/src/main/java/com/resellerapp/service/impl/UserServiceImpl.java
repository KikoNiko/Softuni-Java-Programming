package com.resellerapp.service.impl;

import com.resellerapp.model.dto.UserLoginDTO;
import com.resellerapp.model.dto.UserRegisterDTO;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.UserService;
import com.resellerapp.util.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        Optional<User> optionalUser = userRepository.findByUsername(userRegisterDTO.getUsername());
        if (optionalUser.isPresent()) {
            throw new IllegalArgumentException();
        }

        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        userRepository.save(user);

    }

    @Override
    public void login(UserLoginDTO userLoginDTO) {
        currentUser.setUsername(userLoginDTO.getUsername());
        currentUser.setLogged(true);
    }

    @Override
    public void logout() {
        currentUser.logout();
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        return byUsername.filter(user -> passwordEncoder.matches(password, user.getPassword())).isPresent();
    }
}
