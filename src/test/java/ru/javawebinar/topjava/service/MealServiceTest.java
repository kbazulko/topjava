package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
       })
@RunWith(SpringRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(MEAL_ID_0, USER_ID);
        MATCHER.assertEquals(MEAL_0, meal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(MEAL_ID_0, ADMIN_ID);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEAL_ID_2, ADMIN_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(MEAL_3), service.getAll(ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(MEAL_ID_0, ADMIN_ID);
    }

    @Test
    public void getBetweenDates() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL_1,MEAL_0), service.getBetweenDates(START_DATE, END_DATE, USER_ID));
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        MATCHER.assertCollectionEquals(Collections.singletonList(MEAL_1), service.getBetweenDateTimes(START_DATE_TIME, END_DATE_TIME, USER_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Meal> all = service.getAll(USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL_5, MEAL_4, MEAL_1, MEAL_0), all);
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = new Meal(MEAL_0);
        updated.setDescription("UpdatedDescription");
        updated.setCalories(330);
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(MEAL_ID_0, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundUpdate() throws Exception {
        Meal updated = new Meal(MEAL_0);
        updated.setDescription("UpdatedDescription");
        updated.setCalories(330);
        service.update(updated, ADMIN_ID);
        MATCHER.assertEquals(updated, service.get(MEAL_ID_0, ADMIN_ID));
    }


    @Test
    public void testSave() throws Exception {
        Meal newMeal = new Meal(null, LocalDateTime.of(2017, Month.MAY, 30, 10, 0), "Завтрак", 500);
        Meal created = service.save(newMeal, USER_ID);
        newMeal.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(newMeal, MEAL_5, MEAL_4, MEAL_1, MEAL_0), service.getAll(USER_ID));
    }

}