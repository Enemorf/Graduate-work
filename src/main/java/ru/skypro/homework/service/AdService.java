package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;

import java.io.IOException;
import java.security.Principal;

public interface AdService {
    AdsDto getAllAds();
    AdDto addAd(CreateOrUpdateAdDto createOrUpdateAdDto, MultipartFile image, Principal principal) throws IOException;
    ExtendedAdDto getAd(Integer id);
    boolean removeAd(Integer id);
    AdDto updateAd(Integer id, AdDto adDto);
    AdsDto getUserAd(Principal principal);
    byte[] updateAdImage(Integer id, MultipartFile image);
}
