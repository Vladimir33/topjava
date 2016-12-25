package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

@Controller
public class MealRestController {
    protected final Logger LOG = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public List<Meal> getAll(int userId){
        LOG.info("getAll");
        return service.getAll(userId);
    }

    public Meal get(int id, int userId){
        LOG.info("get " + id);
        return service.get(id, userId);
    }

    public Meal save(Meal meal, int userId){
        LOG.info("save " + meal);
        return service.save(meal, userId);
    }

    public void delete(int id, int userId){
        LOG.info("delete " + id);
        service.delete(id, userId);
    }

    public void update(Meal meal, int userId){
        LOG.info("update " + meal);
        service.update(meal, userId);
    }
}
