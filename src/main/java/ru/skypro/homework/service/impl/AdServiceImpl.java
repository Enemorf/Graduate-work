package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    AdMapper adMapper = Mappers.getMapper(AdMapper.class);
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final ImageService imageService;

    @Override
    public AdsDto getAllAds() {
        List<AdEntity> ads = adRepository.findAll();
        AdsDto adsDto = new AdsDto();
        List<AdDto> adList = new ArrayList<>();
        for(AdEntity adEntity : ads)
        {
            AdDto adDto = adMapper.adEntityToAdDto(adEntity);
            adDto.setImage("/"+adEntity.getImageEntity().getPath());
            adList.add(adDto);
        }
        adsDto.setResults(adList);
        adsDto.setCount(adList.size());
        return adsDto;
    }

    @Override
    public AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image, Principal principal ) throws IOException {
        UserEntity userEntity = getPrincipalUser(principal);
        ImageEntity imageEntity = imageService.saveImg(image);
        AdEntity adEntity = adMapper.extendedAdDtoLiteUserEntityImageEntityToAdEntity(createOrUpdateAdDto,userEntity,imageEntity);
        adRepository.save(adEntity);
        return adMapper.adEntityToAdDto(adEntity);
    }

    @Override
    public ExtendedAdDto getAd(Integer id) {
        AdEntity adEntity = findById(id);
        UserEntity userEntity = adEntity.getUsersId();
        ExtendedAdDto extendedAdDto = adMapper.adEntityUserEntityToExtendedAdDto(adEntity,userEntity);
        extendedAdDto.setImage("/"+adEntity.getImageEntity().getPath());
        return extendedAdDto;
    }

    @Override
    public boolean removeAd(Integer id) {
        return false;
    }

    @Override
    public AdDto updateAd(Integer id, AdDto adDto) {
        return null;
    }

    @Override
    public AdsDto getUserAd(Principal principal) {
        return null;
    }

    @Override
    public byte[] updateAdImage(Integer id, MultipartFile image) {
        return new byte[0];
    }

    private UserEntity getPrincipalUser(Principal principal)
    {
        return userRepository.findByUsername(principal.getName());
    }

    private AdEntity findById(Integer id)
    {
        return adRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
