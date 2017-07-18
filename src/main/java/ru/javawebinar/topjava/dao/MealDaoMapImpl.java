package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MealDaoMapImpl implements MealDao{

    private static Map<Integer, Meal> mealMap;
    private static int maxId = 0;

    public MealDaoMapImpl() {
        mealMap = new ConcurrentHashMap<>();
        List<Meal> mealList = MealsUtil.initializeMealList();
        for (Meal meal: mealList) {
            meal.setId(++maxId);
            mealMap.put(maxId, meal);
        }
    }

    @Override
    public void addMeal(Meal meal) {
        meal.setId(++maxId);
        mealMap.put(maxId, meal);
    }

    @Override
    public void updateMeal(Meal meal) {
        mealMap.put(meal.getId(), meal);
    }

    @Override
    public void removeMeal(int id) {
        mealMap.remove(id);
    }

    @Override
    public Meal getMealById(int id) {
        return mealMap.get(id);
    }

    @Override
    public List<Meal> listMeals() {
        return new ArrayList<>(mealMap.values());
    }
}
