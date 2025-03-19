package api.dto;

import api.model.Product;
import api.model.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MealDto {

    public MealDto (LocalDateTime dateTime, Long user, List<Long> products) {
        this.dateTime =dateTime;
        this.userId = user;
        this.products = products;
    }

    Long userId;

    LocalDateTime dateTime;

    List<Long> products;
}
