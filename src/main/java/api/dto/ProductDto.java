package api.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {

    public ProductDto (String name) {
        this.name= name;
    }

    @NotBlank
    String name;

    @Min(value = 0, message = "количество калории на порцию не может быть отрицательным")
    @Max(value = 700,message = "количество калории на порцию не может быть больше 700")
    Float caloriesPerPortion;

    @Min(value = 0, message = "количество белков на порцию не может быть отрицательным")
    @Max(value = 50, message = "количество белков на порцию не может быть больше 50")
    Float squirrels;

    @Min(value = 0,message = "количество жиров на порцию не может быть отрицательным")
    @Max(value = 80,message = "количество жиров на порцию не может быть больше 80")
    Float fats;

    @Min(value = 0,message = "количество углеводов на порцию не может быть отрицательным")
    @Max(value = 100,message = "количество углеводов на порцию не может быть больше 100")
    Float carbohydrates;
}
