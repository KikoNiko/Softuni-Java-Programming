package com.plannerapp.controller;

import com.plannerapp.util.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final UserSession userSession;

    public HomeController(UserSession userSession) {
        this.userSession = userSession;
    }

    @GetMapping("/")
    public String viewNotLogged() {
        return userSession.isLoggedIn() ? "redirect:/home" : "index";
    }

    @GetMapping("/home")
    public String viewLogged() {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        return "home";
    }
}
