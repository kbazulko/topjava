package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {

    public static final int MEAL_ID_0 = START_SEQ + 2;
    public static final int MEAL_ID_1 = START_SEQ + 3;
    public static final int MEAL_ID_2 = START_SEQ + 4;
    public static final int MEAL_ID_3 = START_SEQ + 5;
    public static final int MEAL_ID_4 = START_SEQ + 6;
    public static final int MEAL_ID_5 = START_SEQ + 7;

    public static final Meal MEAL_0 = new Meal(MEAL_ID_0, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL_1 = new Meal(MEAL_ID_1, LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL_2 = new Meal(MEAL_ID_2, LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL_3 = new Meal(MEAL_ID_3, LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 500);
    public static final Meal MEAL_4 = new Meal(MEAL_ID_4, LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 1000);
    public static final Meal MEAL_5 = new Meal(MEAL_ID_5, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);

    public static final LocalDate START_DATE = LocalDate.of(2015, Month.MAY, 30);
    public static final LocalDate END_DATE = LocalDate.of(2015, Month.MAY, 30);

    public static final LocalDateTime START_DATE_TIME = LocalDateTime.of(2015, Month.MAY, 30, 11, 0);
    public static final LocalDateTime END_DATE_TIME = LocalDateTime.of(2015, Month.MAY, 31, 11, 0);

    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>();

  }
