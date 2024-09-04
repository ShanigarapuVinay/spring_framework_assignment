package com.vinay.finalspringapp.dao;

import com.vinay.finalspringapp.entity.Restaurant;

import java.util.List;

public interface RestaurantDao {
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantById(int id);
    void saveOrUpdateRestaurant(Restaurant restaurant);
    void deleteRestaurant(int id);
}
