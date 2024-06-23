package com.paintingscollectors.service.impl;

import com.paintingscollectors.model.dto.PaintingDisplayDTO;
import com.paintingscollectors.model.dto.UserLoginDTO;
import com.paintingscollectors.model.dto.UserRegisterDTO;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.service.UserService;
import com.paintingscollectors.util.UserSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
                userRepository.findByUsernameOrEmail(userRegisterDTO.getUsername(), userRegisterDTO.getEmail());
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
    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<User> byUsername = userRepository.findByUsername(userLoginDTO.getUsername());
        if (byUsername.isEmpty()) {
            return false;
        }
        userSession.login(byUsername.get());
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
    public long getLoggedUserId() {
        return userSession.getId();
    }

    @Override
    public Set<PaintingDisplayDTO> getAllFavoritePaintings(long userId) {
        return userRepository.findById(userId)
                .map(User::getFavoritePaintings)
                .orElseGet(HashSet::new)
                .stream()
                .map(PaintingDisplayDTO::new)
                .collect(Collectors.toSet());
    }
}
