package com.bonappetit.model.dto;

import com.bonappetit.model.entity.Recipe;

public class RecipeInfoDTO {
    private String name;

    private String ingredients;

    public RecipeInfoDTO(Recipe recipe) {
        this.name = recipe.getName();
        this.ingredients = recipe.getIngredients();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
