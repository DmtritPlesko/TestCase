package api.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Setter
@Getter
@Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "calories_per_portion")
    Float caloriesPerPortion;

    @Column(name = "squirrels")
    Float squirrels;

    @Column(name = "fats")
    Float fats;

    @Column(name = "carbohydrates")
    Float carbohydrates;
}
