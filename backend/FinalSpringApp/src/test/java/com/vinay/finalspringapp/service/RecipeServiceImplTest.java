package com.vinay.finalspringapp.service;

import com.vinay.finalspringapp.dao.RecipeDao;
import com.vinay.finalspringapp.entity.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceImplTest {

    @Mock
    private RecipeDao recipeDao;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Before
    public void setUp() {
    }

    @Test
    public void testGetAllRecipes() {
        List<Recipe> recipes = Arrays.asList(new Recipe(), new Recipe());
        when(recipeDao.getAllRecipes()).thenReturn(recipes);

        List<Recipe> result = recipeService.getAllRecipes();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(recipeDao, times(1)).getAllRecipes();
    }

    @Test
    public void testGetRecipeById() {
        Recipe recipe = new Recipe();
        when(recipeDao.getRecipeById(1)).thenReturn(recipe);

        Recipe result = recipeService.getRecipeById(1);

        assertNotNull(result);
        verify(recipeDao, times(1)).getRecipeById(1);
    }

    @Test
    public void testSaveOrUpdateRecipe() {
        Recipe recipe = new Recipe();

        recipeService.saveOrUpdateRecipe(recipe);
        verify(recipeDao, times(1)).saveOrUpdateRecipe(recipe);
    }

    @Test
    public void testDeleteRecipe() {
        recipeService.deleteRecipe(1);
        verify(recipeDao, times(1)).deleteRecipe(1);
    }

    @Test
    public void testGetRecipesByIds() {
        List<Integer> ids = Arrays.asList(1, 2, 3);
        List<Recipe> recipes = Arrays.asList(new Recipe(), new Recipe(), new Recipe());
        when(recipeDao.getRecipesByIds(ids)).thenReturn(recipes);

        List<Recipe> result = recipeService.getRecipesByIds(ids);
        assertNotNull(result);
        assertEquals(3, result.size());
        verify(recipeDao, times(1)).getRecipesByIds(ids);
    }
}
