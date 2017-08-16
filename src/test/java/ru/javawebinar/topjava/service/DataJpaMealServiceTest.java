package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static ru.javawebinar.topjava.MealTestData.MEAL1_ID;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(value = Profiles.DATAJPA)
public class DataJpaMealServiceTest extends MealServiceTest{

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
