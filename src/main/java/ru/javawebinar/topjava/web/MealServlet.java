package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoMapImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEAL = "/meals.jsp";
    private static int CALORIES_PER_DAY = 2000;
    private MealDao dao;

    public void init() throws ServletException {
        this.dao = new MealDaoMapImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String forward;
        String action = request.getParameter("action") == null ? "listMeal" : request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            log.debug("delete meal");
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            dao.remove(mealId);
            response.sendRedirect("meals");
            return;
        } else if (action.equalsIgnoreCase("edit")){
            log.debug("edit meal");
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = dao.getById(mealId);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listMeal")){
            log.debug("redirect to meals");
            forward = LIST_MEAL;
            request.setAttribute("meals",
                    MealsUtil.getFilteredWithExceeded(dao.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY));
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        LocalDateTime dateTime = DateUtil.convertToLocalDateTime(request.getParameter("dateTime").replace('T', ' '));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));

        Meal meal = new Meal(dateTime, description, calories);

        String mealId = request.getParameter("mealId");
        if(mealId == null || mealId.isEmpty())
        {
            log.debug("add meal");
            dao.add(meal);
        }
        else
        {
            log.debug("edit meal");
            meal.setId(Integer.parseInt(mealId));
            dao.update(meal);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_MEAL);
        request.setAttribute("meals",
                MealsUtil.getFilteredWithExceeded(dao.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY));
        view.forward(request, response);
    }
}
