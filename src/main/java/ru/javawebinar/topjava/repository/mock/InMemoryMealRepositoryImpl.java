package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.UsersUtil;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, meal.getUserId()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("meal save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return UsersUtil.getMeal(meal, userId);
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("meal delete {}", id);
        if (UsersUtil.getMeal(repository.get(id), userId) == null)
            return false;

        repository.remove(id);
        return true;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("meal get {}", id);
        return UsersUtil.getMeal(repository.get(id), userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("meal getAll");
        return getFiltered(LocalDate.MIN, LocalDate.MAX, userId);
    }

    @Override
    public List<Meal> getFiltered(LocalDate startDate, LocalDate endDate, int userId) {
        log.info("meal getAll");
        return repository.values().stream()
                .filter(meal -> meal.getUserId() == userId && DateTimeUtil.isBetween(meal.getDate(), startDate, endDate))
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}

