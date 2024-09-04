package com.vinay.finalspringapp.service;

import com.vinay.finalspringapp.entity.Recipe;

import java.util.List;

public interface RecipeService {
    List<Recipe> getAllRecipes();
    Recipe getRecipeById(int id);
    void saveOrUpdateRecipe(Recipe recipe);
    void deleteRecipe(int id);

    List<Recipe> getRecipesByIds(List<Integer> ids);
}
