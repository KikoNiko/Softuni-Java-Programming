package com.bonappetit.controller;

import com.bonappetit.model.dto.AddRecipeDTO;
import com.bonappetit.service.RecipeService;
import com.bonappetit.util.UserSession;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RecipeController {
    private final UserSession userSession;
    private final RecipeService recipeService;

    public RecipeController(UserSession userSession, RecipeService recipeService) {
        this.userSession = userSession;
        this.recipeService = recipeService;
    }

    @ModelAttribute("recipeData")
    public AddRecipeDTO recipeData() {
        return new AddRecipeDTO();
    }

    @GetMapping("/recipe-add")
    public String viewAddRecipe() {
        return userSession.isLoggedIn() ? "recipe-add" : "redirect:/login";
    }

    @PostMapping("/recipe-add")
    public String addRecipe(@Valid AddRecipeDTO addRecipeData,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("recipeData", addRecipeData);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.recipeData", bindingResult);
        }

        recipeService.addRecipe(addRecipeData);
        return "redirect:/";
    }

}
