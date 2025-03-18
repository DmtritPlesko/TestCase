package api.mapper;

import api.dto.MealDto;
import api.model.Meal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MealMapper {

    @Mapping(target = "user.id",source = "mealDto.userId")
    Meal toMeal(MealDto mealDto);

    @Mapping(target = "userId", source = "meal.user.id")
    MealDto toMealDto(Meal meal);
}
