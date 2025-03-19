package api.service.meal;

import api.dto.MealDto;
import api.mapper.MealMapper;
import api.model.Meal;
import api.model.Product;
import api.model.User;
import api.repository.MealRepository;
import api.repository.ProductRepository;
import api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class MealServiceTest {

    @Mock
    private MealRepository mealRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private MealMapper mealMapper;

    @InjectMocks
    private MealServiceImpl mealService;

    private static final Long USER_ID = 1L;
    private static final Long PRODUCT_ID = 1L;
    private static final LocalDateTime MEAL_DATE = LocalDateTime.now();

    @Test
    void addMeal_Success() {
        User user = new User(USER_ID, "Test User");
        Product product = new Product(PRODUCT_ID, "Test Product");
        Meal meal = new Meal(1L, MEAL_DATE, user, List.of(product));
        MealDto mealDto = new MealDto(MEAL_DATE, USER_ID, List.of(PRODUCT_ID));

        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
        when(productRepository.findAllById(List.of(PRODUCT_ID))).thenReturn(List.of(product));
        when(mealRepository.save(any(Meal.class))).thenReturn(meal);
        when(mealMapper.toMealDto(any(Meal.class))).thenReturn(mealDto);

        MealDto result = mealService.addMeal(mealDto);

        assertNotNull(result);
        assertEquals(MEAL_DATE, result.getDateTime());
        assertEquals(USER_ID, result.getUserId());
        assertEquals(List.of(PRODUCT_ID), result.getProducts());

        verify(mealRepository, times(1)).save(any(Meal.class));
        verify(mealMapper, times(1)).toMealDto(any(Meal.class));
    }

    @Test
    void addMeal_UserNotFound_ThrowsException() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.empty());
        MealDto mealDto = new MealDto(USER_ID, MEAL_DATE, List.of(PRODUCT_ID));

        assertThrows(RuntimeException.class, () -> mealService.addMeal(mealDto));
        verify(mealRepository, never()).save(any(Meal.class));
    }

}