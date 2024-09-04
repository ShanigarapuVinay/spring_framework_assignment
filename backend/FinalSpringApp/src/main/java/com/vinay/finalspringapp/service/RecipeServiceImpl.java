package com.vinay.finalspringapp.service;

import com.vinay.finalspringapp.dao.RecipeDao;
import com.vinay.finalspringapp.entity.Recipe;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeDao recipeDao;

    @Autowired
    public RecipeServiceImpl(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
    }

    @Override
    @Transactional
    public List<Recipe> getAllRecipes() {
        return recipeDao.getAllRecipes();
    }

    @Override
    @Transactional
    public Recipe getRecipeById(int id) {
        Recipe recipe = recipeDao.getRecipeById(id);
        Hibernate.initialize(recipe.getReviews());
        return recipe;
    }

    @Override
    @Transactional
    public void saveOrUpdateRecipe(Recipe recipe) {
        recipeDao.saveOrUpdateRecipe(recipe);
    }

    @Override
    @Transactional
    public void deleteRecipe(int id) {
        recipeDao.deleteRecipe(id);
    }

    @Override
    @Transactional
    public List<Recipe> getRecipesByIds(List<Integer> ids) {
        return recipeDao.getRecipesByIds(ids);
    }
}
