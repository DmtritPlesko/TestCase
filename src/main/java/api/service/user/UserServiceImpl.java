package api.service.user;

import api.dto.UserDto;
import api.enums.Gender;
import api.exception.NotFoundException;
import api.mapper.UserMapper;
import api.model.User;
import api.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    private static final double WOMEN_CALORIES_FORMULA_CONSTANT = 655.096;
    private static final double WOMEN_WEIGHT_COEFFICIENT = 9.563;
    private static final double WOMEN_HEIGHT_COEFFICIENT = 1.85;
    private static final double WOMEN_AGE_COEFFICIENT = 4.679;

    private static final double MEN_CALORIES_FORMULA_CONSTANT = 66.479;
    private static final double MEN_WEIGHT_COEFFICIENT = 13.752;
    private static final double MEN_HEIGHT_COEFFICIENT = 5.003;
    private static final double MEN_AGE_COEFFICIENT = 6.755;

    final UserRepository repository;
    final UserMapper mapper;

    @Override
    public UserDto addUser(UserDto userDto) {

        User user = mapper.toUser(userDto);

        user.setCalories(calculateCalories(
                user.getGender(),
                user.getAge(),
                user.getWeight(),
                user.getHeight())
        );

        return mapper.toUserDto(repository.save(user));
    }

    @Override
    public void delete(Long userId) {

        if (repository.existsById(userId)) {
            repository.deleteById(userId);
        }

        throw new NotFoundException("Пользователь с id = " + userId + " не найден");

    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {

        User user = repository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id = " + userId + " не найден"));

        if (Objects.equals(user.getHeight(), userDto.getHeight()) &&
                Objects.equals(user.getAge(), userDto.getAge()) &&
                Objects.equals(user.getGender(), userDto.getGender()) &&
                Objects.equals(user.getWeight(), userDto.getWeight())) {
            return mapper.toUserDto(repository.save(mapper.updateUser(user, userDto)));
        }

        mapper.updateUser(user, userDto);
        user.setCalories(calculateCalories(
                user.getGender(),
                user.getAge(),
                user.getWeight(),
                user.getHeight())
        );

        return mapper.toUserDto(user);
    }

    private Float calculateCalories(Gender gender, Short age, Float weight, Float height) {

        double result = 0;

        switch (gender) {
            case WOMEN: {
                result = WOMEN_CALORIES_FORMULA_CONSTANT
                        + WOMEN_WEIGHT_COEFFICIENT * weight
                        + WOMEN_HEIGHT_COEFFICIENT * height
                        - WOMEN_AGE_COEFFICIENT * age;
                break;
            }
            case MEN: {
                result = MEN_CALORIES_FORMULA_CONSTANT
                        + MEN_WEIGHT_COEFFICIENT * weight
                        + MEN_HEIGHT_COEFFICIENT * height
                        - MEN_AGE_COEFFICIENT * age;
                break;
            }
        }

        return (float) result;
    }
}
