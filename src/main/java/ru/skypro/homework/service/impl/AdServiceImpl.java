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
        return getAdsDto(ads, adList, adsDto);
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
        AdEntity adEntity = adRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException());
        adRepository.delete(adEntity);
        return true;
    }

    @Override
    public AdDto updateAd(Integer id, CreateOrUpdateAdDto createOrUpdateAdDto) {
        AdEntity adEntity = findById(id);
        adEntity.setTitle(createOrUpdateAdDto.getTitle());
        adEntity.setPrice(createOrUpdateAdDto.getPrice());
        adEntity.setDescription(createOrUpdateAdDto.getDescription());
        adRepository.save(adEntity);

        AdDto adDto = adMapper.adEntityToAdDto(adEntity);
        adDto.setImage("/"+ adEntity.getImageEntity().getPath());
        return adDto;
    }

    @Override
    public AdsDto getUserAd(Principal principal) {
        UserEntity user = getPrincipalUser(principal);
        List<AdEntity> result = adRepository.findByUsersId(user);
        List<AdDto> adDtoList = new ArrayList<>();
        AdsDto adsDto = new AdsDto();
        return getAdsDto(result, adDtoList, adsDto);
    }

    private AdsDto getAdsDto(List<AdEntity> result, List<AdDto> adDtoList, AdsDto adsDto) {
        for (AdEntity adEntity : result) {
            AdDto adDto = adMapper.adEntityToAdDto(adEntity);
            adDto.setImage("/" + adEntity.getImageEntity().getPath());
            adDtoList.add(adDto);
        }
        adsDto.setResults(adDtoList);
        adsDto.setCount(adDtoList.size());
        return adsDto;
    }

    @Override
    public byte[] updateAdImage(Integer id, MultipartFile image) throws IOException {
        AdEntity adEntity = adRepository.findById(id).get();
        ImageEntity imageEntity = adEntity.getImageEntity();
        imageService.updateImg(imageEntity.getId(), image);
        return image.getBytes();
    }

    private UserEntity getPrincipalUser(Principal principal)
    {
        return userRepository.findByUsername(principal.getName());
    }

    @Override
    public AdEntity findById(Integer id)
    {
        return adRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
