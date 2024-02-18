package ru.skypro.homework.mapper;

import org.mapstruct.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.PhotoEntity;
import ru.skypro.homework.entity.UserEntity;

/**
 * Interface UserMapper
 * The mapper is used to map the UserDTO fields to the User entity
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "role", defaultValue = "USER")
    @Mapping(target = "image", source = "image")
    UserEntity toEntity(UserDTO dto);

    @Mapping(target = "image", source = "image")
    @Mapping(target = "id", source = "id")
    UserDTO toUserDto(UserEntity user);

    PhotoEntity map(String value);

    UserEntity toEntity(LoginDTO loginDTO);
    @Mapping(target = "email", source = "username")
    UserEntity toEntity(RegisterDTO registerDTO);

    @Mapping(target = "id", source = "id")
    SecurityUserDto toSecurityDto(UserEntity userEntity);
}