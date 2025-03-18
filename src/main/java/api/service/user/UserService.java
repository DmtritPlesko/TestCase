package api.service.user;

import api.dto.UserDto;

public interface UserService {

    UserDto addUser(UserDto userDto);

    void delete(Long userId);

    UserDto updateUser(Long userId,UserDto userDto);
}
