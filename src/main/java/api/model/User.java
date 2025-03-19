package api.model;

import api.enums.Gender;
import api.enums.Target;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    public User(Long id, String name, Gender gender, Short age, Float weight, Float height) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public User (Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    Gender gender;

    @Column(name = "email",unique = true)
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
