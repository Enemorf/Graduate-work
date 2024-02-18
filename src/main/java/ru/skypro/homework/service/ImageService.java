package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;

import java.io.IOException;

public interface ImageService {
    ImageEntity saveImg(MultipartFile image) throws IOException;
    ImageEntity getImg(Integer id);
    ImageEntity updateImg(Integer id, MultipartFile image) throws IOException;
    byte[] getByteFromFile(String path) throws IOException;
}
