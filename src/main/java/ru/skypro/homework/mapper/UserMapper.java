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
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", defaultValue = "USER")
    @Mapping(target = "avatarPath", source = "image")
    UserEntity toEntity(UserDTO dto);

    @Mapping(target = "image", expression = "java(photoMapper(user))")
    UserDTO toUserDto(UserEntity user);


    default String photoMapper(UserEntity userEntity){
        return "C:/Users/Aleksandr/IdeaProjects/sky-pro/Graduate-work/images" + userEntity.getId();
    }
    PhotoEntity map(String value);

    UserEntity toEntity(LoginDTO loginDTO);
    @Mapping(target = "email", source = "username")
    UserEntity toEntity(RegisterDTO registerDTO);

    SecurityUserDto toSecurityDto(UserEntity userEntity);
}
