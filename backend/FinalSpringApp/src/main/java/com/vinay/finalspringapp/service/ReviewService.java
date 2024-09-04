package com.vinay.finalspringapp.service;

import com.vinay.finalspringapp.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();
    Review getReviewById(int id);
    void saveOrUpdateReview(Review review);
    void deleteReview(int id);
}
