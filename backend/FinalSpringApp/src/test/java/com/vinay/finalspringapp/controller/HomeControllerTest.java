package com.vinay.finalspringapp.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Before
    public void setUp() {
    }

    @Test
    public void testHome() {
        String viewName = homeController.home();
        assertEquals("home", viewName);
    }
}
