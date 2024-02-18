package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.repository.ImagesRepository;
import ru.skypro.homework.service.ImageService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImagesRepository imagesRepository;

    @Value("${path.to.avatars.folder}")
    private String avatarPath;

    @Override
    public ImageEntity saveImg(MultipartFile image) throws IOException {
        ImageEntity img = new ImageEntity();
        saveImg(img,image);
        return getSave(img);
    }

    @Override
    public ImageEntity getImg(Integer id) {
        return imagesRepository.findById(id).get();
    }

    @Override
    public ImageEntity updateImg(Integer id, MultipartFile image) throws IOException {
        ImageEntity img = getImg(id);
        Path path = Path.of(img.getPath());
        Files.deleteIfExists(path);
        ImageEntity newImg = saveImg(img,image);
        return getSave(newImg);
    }

    @Override
    public byte[] getByteFromFile(String path) throws IOException {
        return Files.readAllBytes(Path.of(avatarPath, path));
    }

    private ImageEntity saveImg(ImageEntity image, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        Path path = Path.of(avatarPath,
                UUID.randomUUID()+"."+ Objects.requireNonNull(name).substring(Objects.requireNonNull(name).lastIndexOf("."+1)));
        Files.createDirectories(path.getParent());
        streamReadWrite(path,file);
        image.setPath(path.toString());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        return image;
    }

    private void streamReadWrite(Path path, MultipartFile file) throws IOException {
        try (
                InputStream inputStream = file.getInputStream();
                OutputStream outputStream = Files.newOutputStream(path, StandardOpenOption.CREATE_NEW);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream,4096);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 4096);
                        )
        {
            bufferedInputStream.transferTo(bufferedOutputStream);
        }
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ImageEntity getSave(ImageEntity image) {
        return imagesRepository.save(image);
    }
}
