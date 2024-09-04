package com.vinay.finalspringapp.service;

import com.vinay.finalspringapp.dao.ReviewDao;
import com.vinay.finalspringapp.entity.Review;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceImplTest {
    @Mock
    private ReviewDao reviewDao;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Before
    public void setUp() {
    }

    @Test
    public void testGetAllReviews() {
        List<Review> reviews = Arrays.asList(new Review(), new Review());
        when(reviewDao.getAllReviews()).thenReturn(reviews);

        List<Review> result = reviewService.getAllReviews();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(reviewDao, times(1)).getAllReviews();
    }

    @Test
    public void testGetReviewById() {
        Review review = new Review();
        when(reviewDao.getReviewById(1)).thenReturn(review);

        Review result = reviewService.getReviewById(1);

        assertNotNull(result);
        verify(reviewDao, times(1)).getReviewById(1);
    }

    @Test
    public void testSaveOrUpdateReview() {
        Review review = new Review();

        reviewService.saveOrUpdateReview(review);
        verify(reviewDao, times(1)).saveOrUpdateReview(review);
    }

    @Test
    public void testDeleteReview() {
        reviewService.deleteReview(1);
        verify(reviewDao, times(1)).deleteReview(1);
    }
}
