package com.vinay.finalspringapp.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurants")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NonNull
    @Size(min = 2, max = 45, message = "The name must be at-least 2 and at-most 45 characters")
    private String name;

    @Column(name = "location")
    @NonNull
    @Size(min = 2, message = "It should be at-least 2 characters")
    private String location;

    @Column(name = "rating")
    @NonNull
    @NotNull(message = "Is Required")
    @DecimalMin(value = "0.0", message = "Rating must not be negative")
    @DecimalMax(value = "5.0", message = "Rating must be at most 5.0")
    private double rating;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
                fetch = FetchType.LAZY)
    @JoinTable(name = "restaurant_recipe",
               joinColumns = @JoinColumn(name = "restaurant_id"),
               inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> recipes = new ArrayList<>();

    public void addRecipe(Recipe recipe){
        recipes.add(recipe);
    }
}
