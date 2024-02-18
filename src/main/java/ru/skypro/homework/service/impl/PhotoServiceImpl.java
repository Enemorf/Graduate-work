package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.PhotoEntity;
import ru.skypro.homework.exception.PhotoNotFoundException;
import ru.skypro.homework.repository.PhotoRepository;
import ru.skypro.homework.service.PhotoService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static org.springframework.util.StringUtils.getFilenameExtension;

@Service
@RequiredArgsConstructor
@Slf4j
public class PhotoServiceImpl implements PhotoService {

    @Value("${path.to.avatars.folder}")
    private String imagesDir;

    private final PhotoRepository repository;

    @Override
    public PhotoEntity downloadPhoto(MultipartFile imageFile) throws IOException {
        log.info("Request to avatar upload");
        Path filePath = Path.of(imagesDir + imageFile.getOriginalFilename());
        Files.deleteIfExists(filePath);
        Files.createDirectories(filePath.getParent());
        try (InputStream is = imageFile.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
            log.info("Image was uploaded successfully.");
        }
        PhotoEntity image = new PhotoEntity();
        image.setFilePath(filePath.toString());
        image.setFileSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());
        image.setData(imageFile.getBytes());
        return repository.save(image);
    }

    @Override
    public void deletePhoto(Long id) {
        log.info("Request to avatar delete by id {}", id);
        repository.deleteById(id);
    }

    @Override
    public byte[] getPhoto(Long id) {
        log.info("Request to avatar by id {}", id);
        return repository.findById(id).orElseThrow(PhotoNotFoundException::new).getData();
    }

    @Override
    public byte[] getPhoto(String imagePath) throws IOException{
        Path path = Path.of(imagePath);
        try (InputStream is = Files.newInputStream(path)) {
            byte[] image = is.readAllBytes();
            log.info("Image was downloaded successfully.");
            return image;
        }
    }
}