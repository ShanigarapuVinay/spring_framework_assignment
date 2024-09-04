package com.vinay.finalspringapp.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "reviews")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NonNull
    @Size(min = 2, max = 45, message = "The name must be at-least 2 and at-most 45 characters")
    private String name;

    @Column(name = "comment")
    @NonNull
    @Size(min = 5, message = "It should be at-least 5 characters")
    private String comment;

    @Column(name = "rating")
    @NonNull
    @NotNull(message = "Is Required")
    @DecimalMin(value = "0.0", message = "Rating must not be negative")
    @DecimalMax(value = "5.0", message = "Rating must be at most 5.0")
    private double rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    @ToString.Exclude
    private Recipe recipe;

}
