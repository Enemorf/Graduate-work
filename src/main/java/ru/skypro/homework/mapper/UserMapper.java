package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "image", expression = "java(photoMapper(userEntity))")
    UserDto userEntityToUserDto(UserEntity userEntity);
    default String photoMapper(UserEntity userEntity){
        return "/users/" + userEntity.getId() + "/image";
    }

    @Mapping(target = "password", ignore = true)
    UserEntity registerDtoToUserEntity(RegisterDto registerDto);
}
