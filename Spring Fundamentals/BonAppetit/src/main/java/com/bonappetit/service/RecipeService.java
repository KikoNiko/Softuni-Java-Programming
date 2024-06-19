package com.bonappetit.service;

import com.bonappetit.model.dto.AddRecipeDTO;
import com.bonappetit.model.entity.CategoryName;
import com.bonappetit.model.entity.Recipe;

import java.util.List;

public interface RecipeService {

    void addRecipe(AddRecipeDTO addRecipeDTO);

    List<Recipe> getAllByCategory(CategoryName categoryName);

    void addToFavourites(long id);

}
