package com.vinay.finalspringapp.dao;

import com.vinay.finalspringapp.entity.Recipe;
import com.vinay.finalspringapp.entity.Review;
import com.vinay.finalspringapp.exception.ReviewNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDaoImpl implements ReviewDao{

    private SessionFactory sessionFactory;

    public ReviewDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Review> getAllReviews() {
        Session session = sessionFactory.getCurrentSession();
        Query<Review> query = session.createQuery("from Review order by rating desc", Review.class);
        return query.getResultList();
    }

    @Override
    public Review getReviewById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Review review = session.get(Review.class, id);
        if (review == null) {
            throw new ReviewNotFoundException("Review with ID " + id + " not found");
        }
        return review;
    }

    @Override
    public void saveOrUpdateReview(Review review) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(review);
    }

    @Override
    public void deleteReview(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Review where id = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        if (result == 0) {
            throw new ReviewNotFoundException("Review with ID " + id + " not found");
        }
    }

}
