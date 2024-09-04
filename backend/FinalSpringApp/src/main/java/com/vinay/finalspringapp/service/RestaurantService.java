package com.vinay.finalspringapp.service;

import com.vinay.finalspringapp.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    List<Restaurant> getAllRestaurants();
    Restaurant getRestaurantById(int id);
    void saveOrUpdateRestaurant(Restaurant restaurant);
    void deleteRestaurant(int id);
}
