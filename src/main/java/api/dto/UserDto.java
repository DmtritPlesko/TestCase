package api.dto;

import api.enums.Target;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    @NotBlank
    String name;

    @NotBlank
    String gender;

    @Email
    @NotBlank
    String email;

    @NotNull
    @Min(value = 1,message = "Возраст не может быть меньше 1")
    @Max(value = 130,message = "Возраст не может быть больше 130")
    Short age;

    @NotNull
    @Min(value = 2,message = "Вес не может быть меньше 2 (Кг)")
    @Max(value = 613,message = "Вес не может быть больше 613 (Кг)")
    Float weight;

    @NotNull
    @Min(value = 48,message = "Рост не может быть меньше 48 (См)")
    @Max(value = 251,message = "Максимальный рост не может быть больше 251 (См)")
    Float height;

    @NotNull
    Target target;

    Float calories;
}
