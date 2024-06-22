package com.likebookapp.controller;

import com.likebookapp.util.UserSession;
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
    public String viewLoggedHome() {
        if (userSession.isLoggedIn()) {
            return "home";
        }

        return "redirect:/";
    }
}
