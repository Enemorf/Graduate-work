package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.PhotoEntity;

import java.io.IOException;
import java.nio.file.Path;

public interface PhotoService {
    PhotoEntity downloadPhoto(MultipartFile image) throws IOException;

    void deletePhoto(Long id);

    byte[] getPhoto(Long id);

    byte[] getPhoto(String imagePath) throws IOException;
}