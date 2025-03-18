package api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportDto {

    String userName;

    String email;

    Float userCalories;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    DailyNutritionSummary dailyNutritionSummary;

    String message;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Map<String,DailyNutritionSummary> history;

}
