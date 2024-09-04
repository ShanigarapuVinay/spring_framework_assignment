package com.vinay.finalspringapp.dao;

import com.vinay.finalspringapp.entity.Restaurant;
import com.vinay.finalspringapp.exception.RestaurantNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RestaurantDaoImpl implements RestaurantDao{

    private SessionFactory sessionFactory;

    public RestaurantDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        Session session = sessionFactory.getCurrentSession();
        Query<Restaurant> query = session.createQuery("from Restaurant", Restaurant.class);
        return query.getResultList();
    }

    @Override
    public Restaurant getRestaurantById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Restaurant restaurant = session.get(Restaurant.class, id);
        if (restaurant == null) {
            throw new RestaurantNotFoundException("Restaurant with ID " + id + " not found");
        }
        return restaurant;
    }

    @Override
    public void saveOrUpdateRestaurant(Restaurant restaurant) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(restaurant);
    }

    @Override
    public void deleteRestaurant(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Restaurant where id = :id");
        query.setParameter("id", id);

        int result = query.executeUpdate();
        if (result == 0)
            throw new RestaurantNotFoundException("Restaurant with ID " + id + " not found");
    }
}
