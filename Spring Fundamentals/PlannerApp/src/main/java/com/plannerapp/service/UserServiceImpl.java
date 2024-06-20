package com.plannerapp.service;

import com.plannerapp.model.dto.UserLoginDTO;
import com.plannerapp.model.dto.UserRegisterDTO;
import com.plannerapp.model.entity.User;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.util.UserSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

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
        User user = new User();
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setEmail(userRegisterDTO.getEmail());
        user.setAssignedTasks(new ArrayList<>());
        userRepository.save(user);
    }

    @Override
    public void login(UserLoginDTO userLoginDTO) {
        Optional<User> byUsername = userRepository.findByUsername(userLoginDTO.getUsername());
        if (byUsername.isEmpty()) {
            return;
        }
        userSession.loginUser(byUsername.get());
    }

    @Override
    public void logout() {
        userSession.logout();
    }

    @Override
    public boolean checkCredentials(String username, String password) {
        Optional<User> byUsername = userRepository.findByUsername(username);
        return byUsername.filter(user -> passwordEncoder.matches(password, user.getPassword())).isPresent();
    }

    @Override
    public boolean isLoggedUser() {
        return userSession.isLoggedIn();
    }

}
