package com.bonappetit.service.impl;

import com.bonappetit.model.dto.UserLoginDTO;
import com.bonappetit.model.dto.UserRegisterDTO;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.service.UserService;
import com.bonappetit.util.UserSession;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserSession userSession;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository,
                           UserSession userSession,
                           PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userSession = userSession;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        User mapped = modelMapper.map(userRegisterDTO, User.class);
        mapped.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userRepository.save(mapped);
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<User> byUsername = userRepository.findByUsername(userLoginDTO.getUsername());
        if (byUsername.isEmpty()) {
            return false;
        }
        userSession.loginUser(byUsername.get());
        return true;
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

    @Override
    public List<Recipe> getFavouriteRecipes(long userId) {
        return userRepository.findById(userId)
                .map(User::getFavouriteRecipes)
                .orElseGet(ArrayList::new);
    }
}
