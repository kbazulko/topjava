package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoMapImpl implements MealDao{

    private Map<Integer, Meal> mealMap;
    private AtomicInteger maxId = new AtomicInteger(0);

    public MealDaoMapImpl() {
        mealMap = new ConcurrentHashMap<>();
        List<Meal> mealList = MealsUtil.initializeList();
        for (Meal meal: mealList) {
            meal.setId(maxId.incrementAndGet());
            mealMap.put(meal.getId(), meal);
        }
    }

    @Override
    public void add(Meal meal) {
        meal.setId(maxId.incrementAndGet());
        mealMap.put(meal.getId(), meal);
    }

    @Override
    public void update(Meal meal) {
        mealMap.put(meal.getId(), meal);
    }

    @Override
    public void remove(int id) {
        mealMap.remove(id);
    }

    @Override
    public Meal getById(int id) {
        return mealMap.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mealMap.values());
    }
}
