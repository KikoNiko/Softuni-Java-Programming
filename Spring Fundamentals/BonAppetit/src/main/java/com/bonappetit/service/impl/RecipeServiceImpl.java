package com.bonappetit.service.impl;

import com.bonappetit.model.dto.AddRecipeDTO;
import com.bonappetit.model.dto.RecipeInfoDTO;
import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.CategoryName;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.CategoryRepository;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.service.RecipeService;
import com.bonappetit.util.UserSession;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserSession userSession;

    public RecipeServiceImpl(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UserRepository userRepository, ModelMapper modelMapper, UserSession userSession) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userSession = userSession;
    }

    @Override
    public void addRecipe(AddRecipeDTO addRecipeDTO) {
        if (!userSession.isLoggedIn()) {
            return;
        }
        Recipe mappedRecipe = modelMapper.map(addRecipeDTO, Recipe.class);
        Category categoryByName = categoryRepository.findByName(addRecipeDTO.getCategory());
        mappedRecipe.setCategory(categoryByName);
        mappedRecipe.setAddedBy(userRepository.getById(userSession.getId()));
        recipeRepository.save(mappedRecipe);
    }

    @Override
    public List<Recipe> getAllByCategory(CategoryName categoryName) {
        Category category = categoryRepository.findByName(categoryName);
        return recipeRepository.getAllByCategory(category);
    }

    @Override
    public void addToFavourites(long id) {
        Optional<Recipe> recipeById = recipeRepository.findById(id);
        if (recipeById.isEmpty()) {
            return;
        }
        User currentUser = userRepository.getById(userSession.getId());
        currentUser.getFavouriteRecipes().add(recipeById.get());
        userRepository.save(currentUser);
    }

}
