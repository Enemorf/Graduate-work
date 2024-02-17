package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ads")
public class AdsController {

    @GetMapping
    public ResponseEntity<AdsDto> getAllAds()
    {
        log.info("Get getAllAds");
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<AdDto> addAd()
    {
        log.info("Post addAd");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdDto> getAd(@PathVariable Integer id)
    {
        log.info("Get getAd");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdDto> removeAd(@PathVariable Integer id)
    {
        log.info("Delete removeAd");
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAd(@PathVariable Integer id)
    {
        log.info("Delete removeAd");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getUserAd()
    {
        log.info("Get getUserAd");
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<byte[]> updateAdImage(@PathVariable Integer id)
    {
        log.info("Patch updateAdImage");
        return ResponseEntity.ok().build();
    }


}
