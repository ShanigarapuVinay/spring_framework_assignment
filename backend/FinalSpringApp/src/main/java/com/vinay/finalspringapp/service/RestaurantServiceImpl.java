package com.vinay.finalspringapp.service;

import com.vinay.finalspringapp.dao.RestaurantDao;
import com.vinay.finalspringapp.entity.Restaurant;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService{

    private final RestaurantDao restaurantDao;

    @Autowired
    public RestaurantServiceImpl(RestaurantDao restaurantDao) {
        this.restaurantDao = restaurantDao;
    }

    @Override
    @Transactional
    public List<Restaurant> getAllRestaurants() {
        return restaurantDao.getAllRestaurants();
    }

    @Override
    @Transactional
    public Restaurant getRestaurantById(int id) {
        Restaurant restaurant = restaurantDao.getRestaurantById(id);
        Hibernate.initialize(restaurant.getRecipes()); // Initialize recipes
        return restaurant;
    }

    @Override
    @Transactional
    public void saveOrUpdateRestaurant(Restaurant restaurant) {
        restaurantDao.saveOrUpdateRestaurant(restaurant);
    }

    @Override
    @Transactional
    public void deleteRestaurant(int id) {
        restaurantDao.deleteRestaurant(id);
    }
}
