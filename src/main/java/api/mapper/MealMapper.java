package api.mapper;

import api.dto.MealDto;
import api.model.Meal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MealMapper {

    @Mapping(target = "userId", source = "meal.user.id")
    @Mapping(target = "products",ignore = true)
    MealDto toMealDto(Meal meal);
}
