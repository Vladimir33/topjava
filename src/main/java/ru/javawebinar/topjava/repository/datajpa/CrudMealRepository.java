package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    @Query(name = Meal.DELETE)
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Transactional
    @Override
    Meal save(Meal meal);

    @Query("SELECT m FROM Meal m JOIN FETCH m.user u WHERE u.id = ?2 AND m.id=?1 ORDER BY m.dateTime DESC")
    Meal getByIdWithUser(int id, int userId);

    @Override
    List<Meal> findAll(Sort sort);

    @Query(name = Meal.GET_BETWEEN)
    List<Meal> findBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("userId") int userId);
}
