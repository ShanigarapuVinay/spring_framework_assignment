package com.vinay.finalspringapp.service;

import com.vinay.finalspringapp.dao.ReviewDao;
import com.vinay.finalspringapp.entity.Review;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    private final ReviewDao reviewDao;

    public ReviewServiceImpl(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Override
    @Transactional
    public List<Review> getAllReviews() {
        return reviewDao.getAllReviews();
    }

    @Override
    @Transactional
    public Review getReviewById(int id) {
        return reviewDao.getReviewById(id);
    }

    @Override
    @Transactional
    public void saveOrUpdateReview(Review review) {
        reviewDao.saveOrUpdateReview(review);
    }

    @Override
    @Transactional
    public void deleteReview(int id) {
        reviewDao.deleteReview(id);
    }

}
