package api.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DailyNutritionSummary {

    Float caloriesPerDay;

    List<String> products;

    Float squirrels;

    Float fats;

    Float carbohydrates;

    String message;

}
