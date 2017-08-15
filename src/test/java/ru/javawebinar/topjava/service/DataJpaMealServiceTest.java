package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(value = "datajpa")
public class DataJpaMealServiceTest extends MealServiceTest{
    @Override
    public void testDelete() throws Exception {
        super.testDelete();
    }

    @Override
    public void testDeleteNotFound() throws Exception {
        super.testDeleteNotFound();
    }

    @Override
    public void testCreate() throws Exception {
        super.testCreate();
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
    public void testUpdate() throws Exception {
        super.testUpdate();
    }

    @Override
    public void testUpdateNotFound() throws Exception {
        super.testUpdateNotFound();
    }

    @Override
    public void testGetAll() throws Exception {
        super.testGetAll();
    }

    @Override
    public void testGetBetween() throws Exception {
        super.testGetBetween();
    }

    @Test
    public void testGetWithUser() throws Exception {
        Meal meal = service.getWithUser(MEAL1_ID, USER_ID);
        UserTestData.MATCHER.assertEquals(USER, meal.getUser());
    }

    @Test
    public void testGetWithUserNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        Meal meal = service.getWithUser(MEAL1_ID, ADMIN_ID);
    }
}
