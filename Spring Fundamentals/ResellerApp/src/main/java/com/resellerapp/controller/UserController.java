package com.resellerapp.controller;

import com.resellerapp.model.dto.UserLoginDTO;
import com.resellerapp.model.dto.UserRegisterDTO;
import com.resellerapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

    @PostMapping
    public ModelAndView login(UserLoginDTO userLoginDTO) {
        userService.login(userLoginDTO);

        return new ModelAndView("redirect:/");
    }

    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    @PostMapping("/register")
    public ModelAndView register(UserRegisterDTO userRegisterDTO) {


        userService.register(userRegisterDTO);

        return new ModelAndView("login");
    }
}
