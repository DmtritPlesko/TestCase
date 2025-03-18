package api.model;

import api.enums.Gender;
import api.enums.Target;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(name = "email")
    String email;

    @Column(name = "age")
    Short age;

    @Column(name = "weight")
    Float weight;

    @Column(name = "height")
    Float height;

    @Enumerated(EnumType.STRING)
    @Column(name = "target")
    Target target;

    @Column(name = "calories")
    Float calories;
}
