package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;

public interface ImageService {
    ImageEntity saveImg(MultipartFile image);
    ImageEntity getImg(Integer id);
    ImageEntity updateImg(Integer id, MultipartFile image);
    byte[] getByteFromFile(String path);
}
