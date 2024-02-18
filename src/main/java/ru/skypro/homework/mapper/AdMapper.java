package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.AdsDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.PhotoEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.Collection;


@Mapper (componentModel = "spring")
public interface AdMapper {


    @Mapping(target = "author", source = "adEntity.author.id")
    @Mapping(target = "image", source = "image")
    AdDTO adEntityToAddDTO (AdEntity adEntity);

    @Mapping(target = "pk", source = "id")
    Collection<AdDTO> adToAdListDto(Collection<AdEntity> ads);

    @Mapping(target = "image", source = "image")
    @Mapping(target = "pk", source = "id")
    ExtendedAdDTO adEntityAndUserEntityToExtendedAdDTO(AdEntity adEntity);

    @Mapping(target = "author.id", source = "userEntity.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "image", source = "photoEntity.filePath")
    AdEntity adDTOAndUserEntityToAdEntity (UserEntity userEntity, CreateOrUpdateAdDTO createOrUpdateAdDTO, PhotoEntity photoEntity);
}
