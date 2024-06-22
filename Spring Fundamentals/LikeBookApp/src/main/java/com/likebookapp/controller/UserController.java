package com.likebookapp.controller;

import com.likebookapp.model.dto.UserLoginDTO;
import com.likebookapp.model.dto.UserRegisterDTO;
import com.likebookapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerData")
    public UserRegisterDTO registerData() {
        return new UserRegisterDTO();
    }

    @ModelAttribute("loginData")
    public UserLoginDTO loginData() {
        return new UserLoginDTO();
    }

    @ModelAttribute("validCredentials")
    public void addAttribute(Model model) {
        model.addAttribute("validCredentials");
    }

    @GetMapping("/register")
    public String registerView() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegisterDTO userRegisterData,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        boolean matchingPasswords = userRegisterData.getPassword().equals(userRegisterData.getConfirmPassword());

        if (bindingResult.hasErrors() || !matchingPasswords) {
            redirectAttributes.addFlashAttribute("registerData", userRegisterData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerData", bindingResult);
            return "redirect:/register";
        }
        userService.register(userRegisterData);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid UserLoginDTO userLoginData,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("loginData", userLoginData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.loginData", bindingResult);
            return "redirect:/login";
        }
        boolean validCredentials = userService.checkCredentials(userLoginData.getUsername(), userLoginData.getPassword());

        if (!validCredentials) {
            redirectAttributes.addFlashAttribute("loginData", userLoginData);
            redirectAttributes.addFlashAttribute("validCredentials", false);
            return "redirect:/login";
        }

        userService.login(userLoginData);
        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {
        if (!userService.isUserLoggedIn()) {
            return "redirect:/login";
        }
        userService.logout();
        return "redirect:/";
    }
}
