package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<MealWithExceed> getAll() {
        log.info("meal getAll");
        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public List<MealWithExceed> filter(String startDate, String endDate, String startTime, String endTime) {
        log.info("meal filter {} {}", startTime, endTime);

        LocalDate fromDate = "".equals(startDate) ? LocalDate.MIN : LocalDate.parse(startDate);
        LocalDate toDate = "".equals(endDate) ? LocalDate.MAX : LocalDate.parse(endDate);
        LocalTime fromTime = "".equals(startTime) ? LocalTime.MIN : LocalTime.parse(startTime);
        LocalTime toTime = "".equals(endTime) ? LocalTime.MAX : LocalTime.parse(endTime);

        return MealsUtil.getFilteredWithExceeded(service.getFiltered(fromDate, toDate, AuthorizedUser.id()), fromTime, toTime, MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Meal get(int id) {
        log.info("meal get");
        return service.get(id, AuthorizedUser.id());
    }

    public Meal create(Meal meal) {
        log.info("meal create {}", meal);
        checkNew(meal);
        meal.setUserId(AuthorizedUser.id());
        return service.save(meal, AuthorizedUser.id());
    }

    public void delete(int id) {
        log.info("meal delete {}", id);
        service.delete(id, AuthorizedUser.id());
    }

    public void update(Meal meal, int id) {
        log.info("meal update {} with id={}", meal, id);
        checkIdConsistent(meal, id);
        service.update(meal, AuthorizedUser.id());
    }
}