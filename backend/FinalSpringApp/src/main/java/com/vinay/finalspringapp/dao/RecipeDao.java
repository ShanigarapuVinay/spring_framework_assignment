package com.vinay.finalspringapp.dao;

import com.vinay.finalspringapp.entity.Recipe;

import java.util.List;

public interface RecipeDao {
    List<Recipe> getAllRecipes();
    Recipe getRecipeById(int id);
    void saveOrUpdateRecipe(Recipe recipe);
    void deleteRecipe(int id);

    List<Recipe> getRecipesByIds(List<Integer> ids);
}
