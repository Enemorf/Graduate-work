package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.skypro.homework.dto.AdDTO;
import ru.skypro.homework.dto.CreateOrUpdateAdDTO;
import ru.skypro.homework.dto.ExtendedAdDTO;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.PhotoEntity;
import ru.skypro.homework.entity.UserEntity;

import java.util.Collection;

/**
 * Interface AdMapper
 * The mapper is used to map the AdsDTO, FullAdsDto fields to the Ad entity
 */

@Mapper (componentModel = "spring")
public interface AdMapper {


    @Mapping(source = "id",target = "pk")
    @Mapping(target = "author", source = "author.id")
    @Mapping(target = "image", source = "photo.path")
    AdDTO adEntityToAdDTO (AdEntity adEntity);

    AdEntity adDtoToAdEntity(CreateOrUpdateAdDTO createOrUpdateAdDTO);


    @Mapping(target = "pk",source = "id")
    @Mapping(target = "authorFirstName", source = "author.firstName")
    @Mapping(target = "authorLastName", source = "author.lastName")
    @Mapping(target = "email", source = "author.email")
    @Mapping(target = "phone", source = "author.phone")
    @Mapping(target = "image", source = "adEntity.photo.path")
    ExtendedAdDTO adEntityAndUserEntityToExtendedAdDTO(AdEntity adEntity);

    void updateAd(CreateOrUpdateAdDTO createOrUpdateAdDTO, @MappingTarget AdEntity adEntity);

    Collection<AdDTO> adToAdListDto(Collection<AdEntity> adEntityCollection);


    @Mapping(target = "author", source = "userEntity")
    @Mapping(target = "description", ignore = true)
    AdEntity adDTOAndUserEntityToAdEntity (UserEntity userEntity, AdDTO adDTO, PhotoEntity photoEntity);
}
