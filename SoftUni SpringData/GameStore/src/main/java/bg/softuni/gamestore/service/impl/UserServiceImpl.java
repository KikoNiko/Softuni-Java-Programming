package bg.softuni.gamestore.service.impl;

import bg.softuni.gamestore.data.entities.User;
import bg.softuni.gamestore.data.repositories.UserRepository;
import bg.softuni.gamestore.service.UserService;
import bg.softuni.gamestore.service.dtos.UserLoginDTO;
import bg.softuni.gamestore.service.dtos.UserRegisterDTO;
import bg.softuni.gamestore.utils.ValidatorService;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private Optional<User> loggedIn;
    private final UserRepository userRepository;
    private final ValidatorService validatorService;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ValidatorService validatorService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.validatorService = validatorService;
        this.modelMapper = modelMapper;
    }

    @Override
    public String regiserUser(UserRegisterDTO userRegisterDTO) {
        if (!this.validatorService.isValid(userRegisterDTO)) {
            return validatorService.validate(userRegisterDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(System.lineSeparator()));
        }

        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return "Passwords don't match.";
        }
        Optional<User> optionalUser = this.userRepository.findUserByEmail(userRegisterDTO.getEmail());
        if (optionalUser.isPresent()) {
            return "User with that email is already registered";
        }

        User newUser = this.modelMapper.map(userRegisterDTO, User.class);
        if (this.userRepository.count() == 0) {
            newUser.setAdmin(true);
        }
        this.userRepository.saveAndFlush(newUser);
        return String.format("%s was registered.", newUser.getFullName());
    }

    @Override
    public String loginUser(UserLoginDTO userLoginDTO) {
        Optional<User> optionalUser = this.userRepository
                .findUserByEmailAndPassword(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        if (optionalUser.isEmpty()) {
            return "Email or password is incorrect";
        }

        setLoggedIn(optionalUser);
        return String.format("Successfully logged in %s", optionalUser.get().getFullName());
    }

    @Override
    public String logout() {
        if (this.loggedIn.isEmpty()) {
            return "Cannot log out. No user is logged In";
        }
        String output = String.format("User %s successfully logged out", this.loggedIn.get().getFullName());
        setLoggedIn(Optional.empty());
        return output;
    }


    @Override
    public Optional<User> getLoggedIn() {
        return loggedIn;
    }

    private void setLoggedIn(Optional<User> loggedIn) {
        this.loggedIn = loggedIn;
    }
}
