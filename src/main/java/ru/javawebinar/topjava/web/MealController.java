package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by Vladimir on 28.02.2017.
 */
@Controller
public class MealController extends MealRestController{

    @Autowired
    private MealService service;

    @RequestMapping(value = "/meals", method = RequestMethod.GET)
    public String meals(Model model) {
        int userId = AuthorizedUser.id();
        model.addAttribute("meals", MealsUtil.getWithExceeded(service.getAll(userId), AuthorizedUser.getCaloriesPerDay()));
        return "meals";
    }

    @RequestMapping(value = "/meals/update", params = "id", method = RequestMethod.GET)
    public String update(Model model, @RequestParam(value = "id") int id) {
        int userId = AuthorizedUser.id();
        final Meal meal = service.get(id, userId);
        model.addAttribute("meal", meal);
        return "meal";
    }

    @RequestMapping(value = "/meals/create", method = RequestMethod.GET)
    public String create(Model model) {
        final Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return "meal";
    }

    @RequestMapping(value = "/meals/delete", params = "id", method = RequestMethod.GET)
    public String deleteAndRedirect(@RequestParam(value = "id") int id) {
        int userId = AuthorizedUser.id();
        service.delete(id, userId);
        return "redirect:/meals";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.POST)
    public String save(HttpServletRequest request)
    //   @RequestParam(value = "id") Integer id,
//                       @RequestParam(value = "dateTime") String dateTime,
//                       @RequestParam(value = "description") String description,
//                       @RequestParam(value = "calories") String calories)

    {

        final Meal meal = new Meal(
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.valueOf(request.getParameter("calories")));

    //    int userId = AuthorizedUser.id();

        if (request.getParameter("id").isEmpty()) {
            create(meal);
        //    service.save(meal, userId);
        } else {
            update(meal, Integer.valueOf(request.getParameter("id")));
        //    service.update(meal, userId);
        }
        //   service.save(meal, userId);
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals/filter", method = RequestMethod.POST)
    public String filter(Model model, HttpServletRequest request){
        LocalDate startDate = DateTimeUtil.parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = DateTimeUtil.parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = DateTimeUtil.parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = DateTimeUtil.parseLocalTime(request.getParameter("endTime"));
        model.addAttribute("meals", getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }
}
