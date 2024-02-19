package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("C:/images")
public class ImageController {
    private final ImageService imageService;

    @GetMapping(value = "/{path}")
    public ResponseEntity<byte[]> getImageByte(@PathVariable String path)
    {
        try
        {
            byte[] file = imageService.getByteFromFile(path);
            return ResponseEntity.ok(file);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
