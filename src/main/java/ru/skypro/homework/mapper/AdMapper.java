package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface AdMapper {

    @Mapping(target = "pk", source = "adEntity.id")
    @Mapping(target = "author", source = "adEntity.usersId.id")
    @Mapping(target = "image", source = "adEntity.imageEntity.path")
    AdDto adEntityToAdDto(AdEntity adEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "price", source = "createOrUpdateAdDto.price")
    @Mapping(target = "description", source = "createOrUpdateAdDto.description")
    @Mapping(target = "imageEntity", source = "imageEntity")
    @Mapping(target = "usersId", source = "userEntity")
    AdEntity extendedAdDtoLiteUserEntityImageEntityToAdEntity(CreateOrUpdateAdDto createOrUpdateAdDto, UserEntity userEntity, ImageEntity imageEntity);

    @Mapping(target = "pk", source = "adEntity.id")
    @Mapping(target = "authorFirstName", source = "userEntity.firstName")
    @Mapping(target = "authorLastName", source = "userEntity.lastName")
    @Mapping(target = "image", source = "adEntity.imageEntity.path")
    ExtendedAdDto adEntityUserEntityToExtendedAdDto(AdEntity adEntity, UserEntity userEntity);

}
