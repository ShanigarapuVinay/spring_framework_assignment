package com.vinay.finalspringapp.dao;

import com.vinay.finalspringapp.entity.Recipe;
import com.vinay.finalspringapp.exception.RecipeNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecipeDaoImpl implements RecipeDao {

    private SessionFactory sessionFactory;

    public RecipeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Recipe> getAllRecipes() {
        Session session = sessionFactory.getCurrentSession();
        Query<Recipe> query = session.createQuery("from Recipe", Recipe.class);
        return query.getResultList();
    }

    @Override
    public Recipe getRecipeById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Recipe recipe = session.get(Recipe.class, id);
        if (recipe == null)
            throw new RecipeNotFoundException("Recipe with ID " + id + " not found");
        return recipe;
    }

    @Override
    public void saveOrUpdateRecipe(Recipe recipe) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(recipe);
    }

    @Override
    public void deleteRecipe(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Recipe where id = :id");
        query.setParameter("id", id);

        int result = query.executeUpdate();
        if (result == 0)
            throw new RecipeNotFoundException("Recipe with ID " + id + " not found");
    }

    @Override
    public List<Recipe> getRecipesByIds(List<Integer> ids) {
        Session session = sessionFactory.getCurrentSession();
        Query<Recipe> query = session.createQuery("from Recipe where id in :ids", Recipe.class);
        query.setParameter("ids", ids);
        List<Recipe> recipes = query.getResultList();
        if (recipes.isEmpty()) {
            throw new RecipeNotFoundException("Recipes with IDs " + ids + " not found");
        }
        return recipes;
    }
}
