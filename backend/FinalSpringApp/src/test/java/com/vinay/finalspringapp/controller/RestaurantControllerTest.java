package com.vinay.finalspringapp.controller;

import com.vinay.finalspringapp.entity.Recipe;
import com.vinay.finalspringapp.entity.Restaurant;
import com.vinay.finalspringapp.service.RecipeService;
import com.vinay.finalspringapp.service.RestaurantService;
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
public class RestaurantControllerTest {
    @Mock
    private RestaurantService restaurantService;

    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private RestaurantController restaurantController;

    @Before
    public void setUp() {
    }

    @Test
    public void testListRestaurants() {
        List<Restaurant> restaurants = Arrays.asList(new Restaurant(), new Restaurant());
        when(restaurantService.getAllRestaurants()).thenReturn(restaurants);

        String viewName = restaurantController.listRestaurants(model);
        assertEquals("listRestaurants", viewName);
        verify(model, times(1)).addAttribute(eq("restaurants"), anyList());
        verify(restaurantService, times(1)).getAllRestaurants();
    }

    @Test
    public void testShowAddForm() {
        String viewName = restaurantController.showAddForm(model);
        assertEquals("restaurant", viewName);
        verify(model, times(1)).addAttribute(eq("restaurant"), any(Restaurant.class));
    }

    @Test
    public void testAddRestaurant_Success() {
        Restaurant restaurant = new Restaurant();
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = restaurantController.addRestaurant(restaurant, bindingResult);
        assertEquals("redirect:list", viewName);
        verify(restaurantService, times(1)).saveOrUpdateRestaurant(restaurant);
    }

    @Test
    public void testAddRestaurant_ValidationErrors() {
        Restaurant restaurant = new Restaurant();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(
                Arrays.asList(new FieldError("restaurant", "name", "Name is required"))
        );

        String viewName = restaurantController.addRestaurant(restaurant, bindingResult);
        assertEquals("restaurant", viewName);
        verify(restaurantService, never()).saveOrUpdateRestaurant(restaurant);
    }

    @Test
    public void testShowUpdateForm() {
        Restaurant restaurant = new Restaurant();
        when(restaurantService.getRestaurantById(1)).thenReturn(restaurant);

        String viewName = restaurantController.showUpdateForm(1, model);
        assertEquals("restaurant", viewName);
        verify(model, times(1)).addAttribute(eq("restaurant"), any(Restaurant.class));
        verify(restaurantService, times(1)).getRestaurantById(1);
    }

    @Test
    public void testUpdateRestaurant_Success() {
        Restaurant restaurant = new Restaurant();
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = restaurantController.updateRestaurant(1, restaurant, bindingResult);
        assertEquals("redirect:/restaurants/list", viewName);
        verify(restaurantService, times(1)).saveOrUpdateRestaurant(restaurant);
    }

    @Test
    public void testUpdateRestaurant_ValidationErrors() {
        Restaurant restaurant = new Restaurant();
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(
                Arrays.asList(new FieldError("restaurant", "location", "location is Required"))
        );

        String viewName = restaurantController.updateRestaurant(1, restaurant, bindingResult);
        assertEquals("restaurant", viewName);
        verify(restaurantService, never()).saveOrUpdateRestaurant(restaurant);
    }

    @Test
    public void testDeleteRestaurant() {
        String viewName = restaurantController.deleteRestaurant(1);
        assertEquals("redirect:/restaurants/list", viewName);
        verify(restaurantService, times(1)).deleteRestaurant(1);
    }

    // Recipe-related tests

    @Test
    public void testViewRecipes() {
        Restaurant restaurant = new Restaurant();
        when(restaurantService.getRestaurantById(1)).thenReturn(restaurant);

        String viewName = restaurantController.viewRecipes(1, model);
        assertEquals("restaurant-recipes", viewName);
        verify(model, times(1)).addAttribute(eq("restaurant"), any(Restaurant.class));
        verify(restaurantService, times(1)).getRestaurantById(1);
    }

    @Test
    public void testManageRecipes() {
        Restaurant restaurant = new Restaurant();
        when(restaurantService.getRestaurantById(1)).thenReturn(restaurant);

        List<Recipe> recipes = Arrays.asList(new Recipe(), new Recipe());
        when(recipeService.getAllRecipes()).thenReturn(recipes);

        String viewName = restaurantController.manageRecipes(1, model);

        assertEquals("manage-recipes", viewName);
        verify(model, times(1)).addAttribute(eq("restaurant"), any(Restaurant.class));
        verify(model, times(1)).addAttribute(eq("allRecipes"), anyList());
        verify(restaurantService, times(1)).getRestaurantById(1);
        verify(recipeService, times(1)).getAllRecipes();
    }

    @Test
    public void testUpdateRecipes() {
        Restaurant restaurant = new Restaurant();
        when(restaurantService.getRestaurantById(1)).thenReturn(restaurant);

        List<Recipe> recipes = Arrays.asList(new Recipe());
        when(recipeService.getRecipesByIds(Arrays.asList(1))).thenReturn(recipes);

        String viewName = restaurantController.updateRecipes(1, Arrays.asList(1));
        assertEquals("redirect:list", viewName);

        verify(restaurantService, times(1)).getRestaurantById(1);
        verify(recipeService, times(1)).getRecipesByIds(anyList());
        verify(restaurantService, times(1)).saveOrUpdateRestaurant(restaurant);
    }

    @Test
    public void testUpdateRecipes_NullRecipeIds() {
        Restaurant restaurant = new Restaurant();
        when(restaurantService.getRestaurantById(1)).thenReturn(restaurant);

        String viewName = restaurantController.updateRecipes(1, null);
        assertEquals("redirect:list", viewName);

        verify(restaurantService, times(1)).getRestaurantById(1);
        verify(restaurantService, times(1)).saveOrUpdateRestaurant(restaurant);
    }

    @Test
    public void testUpdateRecipes_EmptyRecipeIds() {
        Restaurant restaurant = new Restaurant();
        when(restaurantService.getRestaurantById(1)).thenReturn(restaurant);

        String viewName = restaurantController.updateRecipes(1, Arrays.asList());
        assertEquals("redirect:list", viewName);

        assertNull(restaurant.getRecipes());
        verify(restaurantService, times(1)).getRestaurantById(1);
        verify(restaurantService, times(1)).saveOrUpdateRestaurant(restaurant);
    }
}
