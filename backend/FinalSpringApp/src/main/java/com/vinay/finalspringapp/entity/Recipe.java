package com.vinay.finalspringapp.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NonNull
    @Size(min = 2, max = 45, message = "The name must be at-least 2 and at-most 45 characters")
    private String name;

    @Column(name = "cuisine")
    @NonNull
    @Size(min = 5, message = "It should be at-least 5 characters")
    private String cuisine;

    @Column(name = "rating")
    @NonNull
    @NotNull(message = "Is Required")
    @DecimalMin(value = "0.0", message = "Rating must not be negative")
    @DecimalMax(value = "5.0", message = "Rating must be at most 5.0")
    private double rating;

    @ManyToMany(mappedBy = "recipes")
    @ToString.Exclude
    private List<Restaurant> restaurants;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "recipe", orphanRemoval = true)
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    public void addReview(Review review){
        reviews.add(review);
        review.setRecipe(this);
    }
}
