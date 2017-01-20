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
    int delete(@Param("id") int id);

//    @Transactional
//    @Modifying
//    @Query("update Meal m set m.dateTime=?1, m.calories=?2, m.description=?3 where m.user.id=:userId")
//    Meal saveByUserId(Meal meal, @Param("userId") int userId);

    @Override
    @Transactional
    Meal save(Meal meal);

//    @Modifying
//    @Query("select m from Meal m where m.id=:id")
//    Meal findOne(@Param("id") int id);

    @Query("SELECT m FROM Meal m JOIN FETCH m.user u WHERE u.id = :user_id AND m.id=:id ORDER BY m.dateTime DESC")
    Meal getByIdWithUser(@Param("id")int id, @Param("user_id")int userId);

//    @Override
//    Meal findOne(Integer id);


    @Override
    List<Meal> findAll(Sort sort);

    List<Meal> findBetweenAndUserIdIs(LocalDateTime startDate, LocalDateTime endDate, int userId);
}
