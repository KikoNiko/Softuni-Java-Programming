package com.bonappetit.controller;

import com.bonappetit.model.dto.RecipeInfoDTO;
import com.bonappetit.model.entity.CategoryName;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import com.bonappetit.util.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    private final UserSession userSession;
    private final RecipeService recipeService;
    private final UserService userService;

    public HomeController(UserSession userSession, RecipeService recipeService, UserService userService) {
        this.userSession = userSession;
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String notLoggedView() {
        return userSession.isLoggedIn() ? "redirect:/home" : "index";
    }

    @GetMapping("/home")
    public String loggedUserHomePage(Model model){
        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        List<Recipe> cocktails = recipeService.getAllByCategory(CategoryName.COCKTAIL);
        List<Recipe> desserts = recipeService.getAllByCategory(CategoryName.DESSERT);
        List<Recipe> mainDishes = recipeService.getAllByCategory(CategoryName.MAIN_DISH);
        model.addAttribute("cocktails", cocktails);
        model.addAttribute("desserts", desserts);
        model.addAttribute("mainDishes", mainDishes);

        List<RecipeInfoDTO> favourites =
                userService.getFavouriteRecipes(userSession.getId())
                        .stream()
                        .map(RecipeInfoDTO::new)
                        .collect(Collectors.toList());

        model.addAttribute("favourites", favourites);

        return "home";
    }
}
