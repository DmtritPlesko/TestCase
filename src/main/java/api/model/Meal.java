package api.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "meals")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Meal {

    public Meal (Long id, LocalDateTime dateTime, User user, List<Product> products) {
        this.id= id;
        this.dateTime =dateTime;
        this.user = user;
        this.products = products;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @ManyToMany
    @JoinTable(
            name = "meal_products",
            joinColumns = @JoinColumn(name = "meal_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    List<Product> products;
}