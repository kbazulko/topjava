package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.Collections;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL1;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL2;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER2_ID;

@ActiveProfiles(value = Profiles.DATAJPA)
public class DataJpaUserServiceTest extends UserServiceTest{

    @Test
    public void testFindMeal() throws Exception {
        User user = service.getWithMeals(ADMIN_ID);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(ADMIN_MEAL2, ADMIN_MEAL1), user.getMeals());
    }

    @Test
    public void testFindEmptyMeal() throws Exception {
        User user = service.getWithMeals(USER2_ID);
        MealTestData.MATCHER.assertCollectionEquals(Collections.EMPTY_LIST, user.getMeals());
    }
}
