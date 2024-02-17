package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;

import java.security.Principal;

public interface AdService {
    AdsDto getAllAds();
    AdDto addAd(AdDto adDto, MultipartFile image);
    AdDto getAd(Integer id);
    boolean removeAd(Integer id);
    AdDto updateAd(Integer id);
    AdsDto getUserAd(Principal principal);
    byte[] updateAdImage(Integer id, MultipartFile image);
}
