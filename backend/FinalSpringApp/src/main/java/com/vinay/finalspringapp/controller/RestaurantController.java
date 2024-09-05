package com.vinay.finalspringapp.controller;

import com.vinay.finalspringapp.entity.Recipe;
import com.vinay.finalspringapp.entity.Restaurant;
import com.vinay.finalspringapp.service.RecipeService;
import com.vinay.finalspringapp.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/restaurants")
public class RestaurantController {

    private RestaurantService restaurantService;
    private RecipeService recipeService;

    public RestaurantController(RestaurantService restaurantService, RecipeService recipeService) {
        this.restaurantService = restaurantService;
        this.recipeService = recipeService;
    }

    @GetMapping("/list")
    public String listRestaurants(Model model) {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        model.addAttribute("restaurants", restaurants);
        return "listRestaurants";
    }

    @GetMapping("/add-restaurant")
    public String showAddForm(Model model) {
        Restaurant restaurant = new Restaurant();
        model.addAttribute("restaurant", restaurant);
        return "restaurant";
    }

    @PostMapping("/add-restaurant")
    public String addRestaurant(@Valid @ModelAttribute("restaurant") Restaurant restaurant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Validation errors found in addRestaurant: {}", bindingResult.getAllErrors());
            return "restaurant";
        }
        restaurantService.saveOrUpdateRestaurant(restaurant);
        return "redirect:list";
    }

    @GetMapping("/update-restaurant/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        model.addAttribute("restaurant", restaurant);
        return "restaurant";
    }

    @PostMapping("/update-restaurant/{id}")
    public String updateRestaurant(@PathVariable int id, @Valid @ModelAttribute("restaurant") Restaurant restaurant, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Validation errors found in updateRestaurant: {}", bindingResult.getAllErrors());
            return "restaurant";
        }
        restaurant.setId(id);
        restaurantService.saveOrUpdateRestaurant(restaurant);
        return "redirect:/restaurants/list";
    }

    @GetMapping("/delete-restaurant/{id}")
    public String deleteRestaurant(@PathVariable int id) {
        restaurantService.deleteRestaurant(id);
        return "redirect:/restaurants/list";
    }

    // For Recipes

    @GetMapping("/{id}/recipes")
    public String viewRecipes(@PathVariable int id, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        model.addAttribute("restaurant", restaurant);
        return "restaurant-recipes";
    }

    @GetMapping("/{id}/manage-recipes")
    public String manageRecipes(@PathVariable int id, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        List<Recipe> allRecipes = recipeService.getAllRecipes();
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("allRecipes", allRecipes);
        return "manage-recipes";
    }

    @PostMapping("/update-recipes")
    public String updateRecipes(@RequestParam("restaurantId") int restaurantId,
                                @RequestParam(value = "recipeIds", required = false) List<Integer> recipeIds) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);

        if (recipeIds == null || recipeIds.isEmpty()) {
            // If no recipes are selected, clear the list of recipes
            restaurant.setRecipes(null);
        } else {
            // Otherwise, get the selected recipes and update the restaurant
            List<Recipe> selectedRecipes = recipeService.getRecipesByIds(recipeIds);
            restaurant.setRecipes(selectedRecipes);
        }

        restaurantService.saveOrUpdateRestaurant(restaurant);
        return "redirect:list";
    }

}
