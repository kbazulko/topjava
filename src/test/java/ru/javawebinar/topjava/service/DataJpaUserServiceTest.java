package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.Collections;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL1;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL2;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER2_ID;

@ActiveProfiles(value = "datajpa")
public class DataJpaUserServiceTest extends UserServiceTest{

    @Override
    public void testCreate() throws Exception {
        super.testCreate();
    }

    @Override
    public void testDuplicateMailCreate() throws Exception {
        super.testDuplicateMailCreate();
    }

    @Override
    public void testDelete() throws Exception {
        super.testDelete();
    }

    @Override
    public void testDeleteNotFound() throws Exception {
        super.testDeleteNotFound();
    }

    @Override
    public void testGet() throws Exception {
        super.testGet();
    }

    @Override
    public void testGetNotFound() throws Exception {
        super.testGetNotFound();
    }

    @Override
    public void testGetByEmail() throws Exception {
        super.testGetByEmail();
    }

    @Override
    public void testGetAll() throws Exception {
        super.testGetAll();
    }

    @Override
    public void testUpdate() throws Exception {
        super.testUpdate();
    }

    @Override
    public void testFindMeal() throws Exception {
        User user = service.getWithMeals(ADMIN_ID);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_MEAL2, ADMIN_MEAL1), user.getMeals());
    }

    @Override
    public void testFindEmptyMeal() throws Exception {
        User user = service.getWithMeals(USER2_ID);
        MealTestData.MATCHER.assertCollectionEquals(Collections.EMPTY_LIST, user.getMeals());
    }
}
