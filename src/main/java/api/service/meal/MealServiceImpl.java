package api.service.meal;

import api.dto.DailyNutritionSummary;
import api.dto.MealDto;
import api.dto.ReportDto;
import api.exception.NotFoundException;
import api.mapper.MealMapper;
import api.model.Meal;
import api.model.Product;
import api.model.User;
import api.repository.MealRepository;
import api.repository.ProductRepository;
import api.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MealServiceImpl implements MealService {

    final MealRepository mealRepository;
    final ProductRepository productRepository;
    final UserRepository userRepository;
    final MealMapper mealMapper;

    @Override
    public MealDto addMeal(MealDto mealDto) {

        log.info("Добавление приёма пищи");

        User user = userRepository.findById(mealDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        Meal meal = new Meal();
        meal.setDateTime(mealDto.getDateTime());
        meal.setUser(user);

        List<Product> products = productRepository.findAllById(mealDto.getProducts());
        if (products.size() != mealDto.getProducts().size()) {
            throw new RuntimeException("Некоторые продукты не найдены");
        }
        meal.setProducts(products);

        Meal savedMeal = mealRepository.save(meal);

        return mealMapper.toMealDto(savedMeal);
    }

    @Override
    public ReportDto createReportByDay(Long userId, String date) {

        log.info("Формирование отчёта за {} для пользователя с id = {}", date, userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Пользователь с id = " + userId + " не найден"));

        LocalDate datedate = LocalDate.parse(date.trim());
        List<Meal> meals = mealRepository.findAllByUserIdAndDateWithProducts(userId, datedate);

        if (meals.isEmpty()) {
            throw new NotFoundException("Нет записей за " + date + " для пользователя с id = " + userId);
        }

        DailyNutritionSummary dailyNutritionSummary = calculateDailyNutrition(meals);

        return ReportDto.builder()
                .userName(user.getName())
                .email(user.getEmail())
                .userCalories(user.getCalories())
                .dailyNutritionSummary(dailyNutritionSummary)
                .message(generateMessage(user.getName(), dailyNutritionSummary.getCaloriesPerDay(), user.getCalories()))
                .build();
    }

    @Override
    public ReportDto history(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь с id = " + id + " не найден"));

        Map<String, List<Meal>> history = getMealsGroupedByDate(id);
        Map<String, DailyNutritionSummary> reportHistory = new HashMap<>();


        for (String date : history.keySet()) {
            reportHistory.put(date, calculateDailyNutrition(history.get(date)));
        }

        return ReportDto.builder()
                .userName(user.getName())
                .email(user.getEmail())
                .userCalories(user.getCalories())
                .history(reportHistory)
                .build();
    }

    private DailyNutritionSummary calculateDailyNutrition(List<Meal> meals) {
        Float caloriesPerDay = 0f;
        Float squirrels = 0f;
        Float fats = 0f;
        Float carbohydrates = 0f;
        List<String> products = new ArrayList<>();

        for (Meal meal : meals) {

            for (Product product : meal.getProducts()) {
                caloriesPerDay += product.getCaloriesPerPortion();
                squirrels += product.getSquirrels();
                fats += product.getFats();
                carbohydrates += product.getCarbohydrates();
            }
            products.addAll(meal.getProducts().stream().map(Product::getName).toList());
        }

        return DailyNutritionSummary.builder()
                .caloriesPerDay(caloriesPerDay)
                .squirrels(squirrels)
                .fats(fats)
                .carbohydrates(carbohydrates)
                .products(products)
                .build();
    }

    private String generateMessage(String userName, float caloriesPerDay, float userCalories) {

        return String.format(
                "Пользователь %s %s уложился в дневную норму",
                userName,
                (caloriesPerDay >= userCalories) ? "" : "не"
        );
    }

    private Map<String, List<Meal>> getMealsGroupedByDate(Long userId) {
        List<Meal> meals = mealRepository.findByUserIdWithProducts(userId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return meals.stream()
                .collect(Collectors.groupingBy(
                        meal -> meal.getDateTime().format(formatter)
                ));
    }

}
