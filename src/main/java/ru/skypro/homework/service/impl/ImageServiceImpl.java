package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.service.ImageService;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public ImageEntity saveImg(MultipartFile image) {
        return null;
    }

    @Override
    public ImageEntity getImg(Integer id) {
        return null;
    }

    @Override
    public ImageEntity updateImg(Integer id, MultipartFile image) {
        return null;
    }

    @Override
    public byte[] getByteFromFile(String path) {
        return new byte[0];
    }
}
