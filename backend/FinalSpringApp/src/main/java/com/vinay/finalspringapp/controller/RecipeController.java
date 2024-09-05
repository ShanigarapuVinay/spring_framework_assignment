package com.vinay.finalspringapp.controller;

import com.vinay.finalspringapp.entity.Recipe;
import com.vinay.finalspringapp.entity.Restaurant;
import com.vinay.finalspringapp.entity.Review;
import com.vinay.finalspringapp.service.RecipeService;
import com.vinay.finalspringapp.service.RestaurantService;
import com.vinay.finalspringapp.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/recipes")
public class RecipeController {
    private RecipeService recipeService;
    private ReviewService reviewService;

    public RecipeController(RecipeService recipeService, ReviewService reviewService) {
        this.recipeService = recipeService;
        this.reviewService = reviewService;
    }

    @GetMapping("/list")
    public String listRecipes(Model model) {
        List<Recipe> recipes = recipeService.getAllRecipes();
        model.addAttribute("recipes", recipes);
        return "listRecipes";
    }

    @GetMapping("/add-recipe")
    public String showAddForm(Model model) {
        Recipe recipe = new Recipe();
        model.addAttribute("recipe", recipe);
        return "recipe";
    }

    @PostMapping("/add-recipe")
    public String addRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Validation errors found in addRecipe: {}", bindingResult.getAllErrors());
            return "recipe";
        }
        recipeService.saveOrUpdateRecipe(recipe);
        return "redirect:list";
    }

    @GetMapping("/update-recipe/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        return "recipe";
    }

    @PostMapping("/update-recipe/{id}")
    public String updateRecipe(@PathVariable int id, @Valid @ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Validation errors found in updateRecipe: {}", bindingResult.getAllErrors());
            return "recipe";
        }
        recipe.setId(id);
        recipeService.saveOrUpdateRecipe(recipe);
        return "redirect:/recipes/list";
    }

    @GetMapping("/delete-recipe/{id}")
    public String deleteRecipe(@PathVariable int id) {
        recipeService.deleteRecipe(id);
        return "redirect:/recipes/list";
    }

    // For Reviews

    @GetMapping("/{recipeId}/reviews")
    public String reviewsList(@PathVariable int recipeId, Model model) {
        Recipe recipe = recipeService.getRecipeById(recipeId);
        model.addAttribute("recipe", recipe);
        return "listReviews";
    }

    @GetMapping("/{recipeId}/add-review")
    public String showAddReviewForm(@PathVariable int recipeId, Model model) {
        Review review = new Review();
        model.addAttribute("review", review);
        return "review";
    }

    @PostMapping("/{recipeId}/add-review")
    public String addReview(@PathVariable int recipeId, @Valid @ModelAttribute("review") Review review, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Validation errors found in addReview: {}", bindingResult.getAllErrors());
            return "review";
        }
        Recipe recipe = recipeService.getRecipeById(recipeId);
        recipe.addReview(review);
        reviewService.saveOrUpdateReview(review);
        return "redirect:../" + recipeId + "/reviews";
    }

    @GetMapping("/{recipeId}/update-review/{reviewId}")
    public String showUpdateReviewForm(@PathVariable int recipeId, @PathVariable int reviewId, Model model) {
        Review review = reviewService.getReviewById(reviewId);
        model.addAttribute("review", review);
        return "review";
    }

    @PostMapping("/{recipeId}/update-review/{reviewId}")
    public String updateReview(@PathVariable int recipeId, @PathVariable int reviewId, @Valid @ModelAttribute("review") Review review, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.warn("Validation errors found in updateReview: {}", bindingResult.getAllErrors());
            return "review";
        }
        review.setId(reviewId);
        Recipe recipe = recipeService.getRecipeById(recipeId);
        review.setRecipe(recipe);
        reviewService.saveOrUpdateReview(review);
        return "redirect:/recipes/" + recipeId + "/reviews";
    }

    @GetMapping("/{recipeId}/delete-review/{reviewId}")
    public String deleteReview(@PathVariable int recipeId, @PathVariable int reviewId) {
        reviewService.deleteReview(reviewId);
        return "redirect:/recipes/" + recipeId + "/reviews";
    }
}
