package api.repository;

import api.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal,Long> {

    @Query("SELECT m FROM Meal m JOIN FETCH m.products WHERE m.user.id = :userId")
    List<Meal> findByUserIdWithProducts(@Param("userId") Long userId);

    @Query("SELECT m FROM Meal m JOIN FETCH m.products WHERE m.user.id = :userId AND DATE(m.dateTime) = :date")
    List<Meal> findAllByUserIdAndDateWithProducts(@Param("userId") Long userId, @Param("date") LocalDate date);

}
