package com.vinay.finalspringapp.service;

import com.vinay.finalspringapp.dao.RestaurantDao;
import com.vinay.finalspringapp.entity.Restaurant;
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
public class RestaurantServiceImplTest {

    @Mock
    private RestaurantDao restaurantDao;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    @Before
    public void setUp() {
    }

    @Test
    public void testGetAllRestaurants() {
        List<Restaurant> restaurants = Arrays.asList(new Restaurant(), new Restaurant());
        when(restaurantDao.getAllRestaurants()).thenReturn(restaurants);

        List<Restaurant> result = restaurantService.getAllRestaurants();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(restaurantDao, times(1)).getAllRestaurants();
    }

    @Test
    public void testGetRestaurantById() {
        Restaurant restaurant = new Restaurant();
        when(restaurantDao.getRestaurantById(1)).thenReturn(restaurant);

        Restaurant result = restaurantService.getRestaurantById(1);

        assertNotNull(result);
        verify(restaurantDao, times(1)).getRestaurantById(1);
    }

    @Test
    public void testSaveOrUpdateRestaurant() {
        Restaurant restaurant = new Restaurant();

        restaurantService.saveOrUpdateRestaurant(restaurant);

        verify(restaurantDao, times(1)).saveOrUpdateRestaurant(restaurant);
    }

    @Test
    public void testDeleteRestaurant() {
        restaurantService.deleteRestaurant(1);
        verify(restaurantDao, times(1)).deleteRestaurant(1);
    }
}
