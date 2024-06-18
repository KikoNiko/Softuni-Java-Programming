package com.dictionaryapp.service.impl;

import com.dictionaryapp.model.dto.UserLoginDTO;
import com.dictionaryapp.model.dto.UserRegisterDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.util.UserSession;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserSession userSession;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper,
                           PasswordEncoder passwordEncoder,
                           UserSession userSession) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
    }

    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {
        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }

        boolean isUsernameOrEmailTaken =
                userRepository.existsByUsernameOrEmail(userRegisterDTO.getUsername(), userRegisterDTO.getEmail());
        if (isUsernameOrEmailTaken) {
            return false;
        }

        User mapped = modelMapper.map(userRegisterDTO, User.class);
        mapped.setPassword(passwordEncoder.encode(mapped.getPassword()));
        userRepository.save(mapped);

        return true;
    }

    @Override
    public boolean login(UserLoginDTO userLoginDto) {
        Optional<User> byUsername = userRepository.findByUsername(userLoginDto.getUsername());
        if (byUsername.isEmpty()) {
            return false;
        }
        User user = byUsername.get();
        if (!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            return false;
        }

        userSession.login(user);
        return true;
    }

    @Override
    public void logout() {
        userSession.logout();
    }
}
