package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
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

    List<Meal> findAllByUserIdOrderByDateTimeDesc(int userId);

    @Override
    @Transactional
    Meal save(Meal meal);

    @Transactional
    @Modifying
    @Query("UPDATE Meal m SET m.dateTime=:dateTime, m.description=:description, m.calories=:calories " +
                    "WHERE m.id=:id AND m.user.id=:userId")
    int update(@Param("id") int id, @Param("userId") int userId, @Param("dateTime") LocalDateTime dateTime,
                @Param("description") String description, @Param("calories") int calories);

    @Override
    Meal findOne(Integer id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    List<Meal> findByDateTimeBetweenAndUserIdOrderByDateTimeDesc(LocalDateTime startDate, LocalDateTime endDate, int userId);

    @Query("SELECT m, u FROM Meal m INNER JOIN User u ON m.user.id=u.id WHERE m.id=:id and m.user.id=:userId")
    Meal getWithUser(@Param("id") int id, @Param("userId") int userId);
}
