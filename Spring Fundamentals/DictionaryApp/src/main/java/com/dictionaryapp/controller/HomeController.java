package com.dictionaryapp.controller;

import com.dictionaryapp.util.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final UserSession userSession;

    public HomeController(UserSession userSession) {
        this.userSession = userSession;
    }

    @GetMapping("/")
    public String notLogged() {
        if (userSession.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String logged() {
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }
        return "home";
    }
}
