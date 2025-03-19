package api.service.user;

import api.dto.UserDto;
import api.enums.Gender;
import api.exception.NotFoundException;
import api.mapper.UserMapper;
import api.model.User;
import api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserServiceImpl userService;

    private static final Long USER_ID = 1L;
    private static final String USERNAME = "Test User";
    private static final Gender GENDER = Gender.WOMEN;
    private static final Short AGE = 30;
    private static final Float WEIGHT = 65.0f;
    private static final Float HEIGHT = 170.0f;

    @Test
    void addUser_Success() {

        User user = new User(USER_ID, USERNAME, GENDER, AGE, WEIGHT, HEIGHT);
        UserDto userDto = new UserDto(USERNAME, GENDER, AGE, WEIGHT, HEIGHT);

        when(mapper.toUser(any(UserDto.class))).thenReturn(user);
        when(mapper.toUserDto(any(User.class))).thenReturn(userDto);
        when(repository.save(any(User.class))).thenReturn(user);

        UserDto result = userService.addUser(userDto);

        assertNotNull(result);
        assertEquals(USERNAME, result.getName());
        assertEquals(GENDER, result.getGender());
        assertEquals(AGE, result.getAge());
        assertEquals(WEIGHT, result.getWeight());
        assertEquals(HEIGHT, result.getHeight());

        verify(repository, times(1)).save(any(User.class));
        verify(mapper, times(1)).toUserDto(any(User.class));
    }
}