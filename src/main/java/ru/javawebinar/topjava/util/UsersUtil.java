package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;


public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(null, "admin", "email", "password", Role.ROLE_ADMIN),
            new User(null, "user", "email", "password", Role.ROLE_USER)
    );

    public static Meal getMeal(Meal meal, int userId) {
        return meal == null ? null : meal.getUserId() == userId ? meal : null;
    }
}
