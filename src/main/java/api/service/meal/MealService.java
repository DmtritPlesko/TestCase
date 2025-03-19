package api.service.meal;

import api.dto.MealDto;
import api.dto.ReportDto;
import java.time.LocalDate;

public interface MealService {

    MealDto addMeal(MealDto mealDto);

    ReportDto createReportByDay(Long id, String dateTime);

    ReportDto history(Long id);
}
