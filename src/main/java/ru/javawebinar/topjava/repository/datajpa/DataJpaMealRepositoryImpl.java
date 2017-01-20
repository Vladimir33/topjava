package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.datajpa.CrudMealRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    private static final Sort SORT_BY_DATE_DESC = new Sort("dateTime");

    @Autowired
    private CrudMealRepository crudRepository;

    @Override
    public Meal save(Meal meal, int userId) {
        return crudRepository.save(meal);
        //    return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return crudRepository.getByIdWithUser(id, userId);
        //    return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.findAll(SORT_BY_DATE_DESC);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudRepository.findBetweenAndUserIdIs(startDate, endDate, userId);
    }
}
