package com.vinay.finalspringapp.controller;

import com.vinay.finalspringapp.entity.Recipe;
import com.vinay.finalspringapp.entity.Review;
import com.vinay.finalspringapp.service.RecipeService;
import com.vinay.finalspringapp.service.ReviewService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private ReviewService reviewService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private RecipeController recipeController;

    @Before
    public void setUp() {
    }

    @Test
    public void testListRecipes() {
        List<Recipe> recipes = Arrays.asList(new Recipe(), new Recipe());
        when(recipeService.getAllRecipes()).thenReturn(recipes);

        String viewName = recipeController.listRecipes(model);
        assertEquals("listRecipes", viewName);
        verify(model, times(1)).addAttribute(eq("recipes"), anyList());
        verify(recipeService, times((1))).getAllRecipes();
    }

    @Test
    public void testShowAddForm() {
        String viewName = recipeController.showAddForm(model);
        assertEquals("recipe", viewName);
        verify(model, times(1)).addAttribute(eq("recipe"), any(Recipe.class));
    }

    @Test
    public void testAddRecipe_Success() {
        Recipe recipe = new Recipe();
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = recipeController.addRecipe(recipe, bindingResult);
        assertEquals("redirect:list", viewName);
        verify(recipeService, times(1)).saveOrUpdateRecipe(recipe);
    }

    @Test
    public void testAddRecipe_ValidationErrors() {
        Recipe recipe = new Recipe();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(Arrays.asList(new FieldError("recipe", "name", "Name is required")));

        String viewName = recipeController.addRecipe(recipe, bindingResult);
        assertEquals("recipe", viewName);
        verify(recipeService, never()).saveOrUpdateRecipe(recipe);
    }

    @Test
    public void testShowUpdateForm() {
        Recipe recipe = new Recipe();
        when(recipeService.getRecipeById(1)).thenReturn(recipe);

        String viewName = recipeController.showUpdateForm(1, model);
        assertEquals("recipe", viewName);

        verify(model, times(1)).addAttribute(eq("recipe"), any(Recipe.class));
        verify(recipeService, times(1)).getRecipeById(1);
    }

    @Test
    public void testUpdateRecipe_Success() {
        Recipe recipe = new Recipe();
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = recipeController.updateRecipe(1, recipe, bindingResult);
        assertEquals("redirect:/recipes/list", viewName);
        verify(recipeService, times(1)).saveOrUpdateRecipe(recipe);
    }

    @Test
    public void testUpdateRecipe_ValidationErrors() {
        Recipe recipe = new Recipe();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(Arrays.asList(new FieldError("recipe", "name", "Name is required")));

        String viewName = recipeController.updateRecipe(1, recipe, bindingResult);
        assertEquals("recipe", viewName);
        verify(recipeService, never()).saveOrUpdateRecipe(recipe);
    }

    @Test
    public void testDeleteRecipe() {
        String viewName = recipeController.deleteRecipe(1);
        assertEquals("redirect:/recipes/list", viewName);
        verify(recipeService, times(1)).deleteRecipe(1);
    }

    // Review-related tests

    @Test
    public void testReviewsList() {
        Recipe recipe = new Recipe();
        when(recipeService.getRecipeById(1)).thenReturn(recipe);

        String viewName = recipeController.reviewsList(1, model);
        assertEquals("listReviews", viewName);
        verify(model, times(1)).addAttribute(eq("recipe"), any(Recipe.class));
        verify(recipeService, times(1)).getRecipeById(1);
    }

    @Test
    public void testShowAddReviewForm() {
        String viewName = recipeController.showAddReviewForm(1, model);
        assertEquals("review", viewName);
        verify(model, times(1)).addAttribute(eq("review"), any(Review.class));
    }

    @Test
    public void testAddReview_Success() {
        Recipe recipe = new Recipe();
        when(recipeService.getRecipeById(1)).thenReturn(recipe);
        when(bindingResult.hasErrors()).thenReturn(false);

        Review review = new Review();

        String viewName = recipeController.addReview(1, review, bindingResult);
        assertEquals("redirect:../1/reviews", viewName);
        verify(recipeService, times(1)).getRecipeById(1);
        verify(reviewService, times(1)).saveOrUpdateReview(review);
    }

    @Test
    public void testAddReview_ValidationErrors() {
        Review review = new Review();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(
                Arrays.asList(new FieldError("review", "rating", "Rating is required")));

        String viewName = recipeController.addReview(1, review, bindingResult);
        assertEquals("review", viewName);
        verify(recipeService, never()).getRecipeById(anyInt());
        verify(reviewService, never()).saveOrUpdateReview(any(Review.class));
    }

    @Test
    public void testShowUpdateReviewForm() {
        Review review = new Review();
        when(reviewService.getReviewById(1)).thenReturn(review);

        String viewName = recipeController.showUpdateReviewForm(1, 1, model);
        assertEquals("review", viewName);
        verify(model, times(1)).addAttribute(eq("review"), any(Review.class));
        verify(reviewService, times(1)).getReviewById(1);
    }

    @Test
    public void testUpdateReview_Success() {
        Recipe recipe = new Recipe();
        when(recipeService.getRecipeById(1)).thenReturn(recipe);
        when(bindingResult.hasErrors()).thenReturn(false);

        Review review = new Review();

        String viewName = recipeController.updateReview(1, 1, review, bindingResult);
        assertEquals("redirect:/recipes/1/reviews", viewName);
        verify(reviewService, times(1)).saveOrUpdateReview(review);
    }

    @Test
    public void testUpdateReview_ValidationErrors() {
        Review review = new Review();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(
                Arrays.asList(new FieldError("review", "comment", "Comment is required")));

        String viewName = recipeController.updateReview(1, 1, review, bindingResult);
        assertEquals("review", viewName);
        verify(reviewService, never()).saveOrUpdateReview(any(Review.class));
    }

    @Test
    public void testDeleteReview() {
        String viewName = recipeController.deleteReview(1, 1);
        assertEquals("redirect:/recipes/1/reviews", viewName);
        verify(reviewService, times(1)).deleteReview(1);
    }
}
