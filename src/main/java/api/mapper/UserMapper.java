package api.mapper;

import api.dto.UserDto;
import api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "calories",ignore = true)
    User toUser(UserDto userDto);

    UserDto toUserDto(User user);

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "calories",ignore = true)
    User updateUser(@MappingTarget User user , UserDto userDto);
}
