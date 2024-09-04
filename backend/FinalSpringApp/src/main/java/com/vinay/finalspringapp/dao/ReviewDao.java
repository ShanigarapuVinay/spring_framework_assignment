package com.vinay.finalspringapp.dao;


import com.vinay.finalspringapp.entity.Review;

import java.util.List;

public interface ReviewDao {
    List<Review> getAllReviews();
    Review getReviewById(int id);
    void saveOrUpdateReview(Review review);
    void deleteReview(int id);
}
