package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.AdService;

import java.io.IOException;
import java.security.Principal;
import java.util.NoSuchElementException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
@RequiredArgsConstructor
public class AdsController {

    private final AdService adService;

    @GetMapping
    public ResponseEntity<AdsDto> getAllAds()
    {
        log.info("Get getAllAds");
        AdsDto adsDto = adService.getAllAds();
        return ResponseEntity.ok().body(adsDto);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AdDto> addAd(@RequestPart("properties") CreateOrUpdateAdDto createOrUpdateAdDto,
                                       @RequestPart("image") MultipartFile image,
                                       Principal principal)
    {
        log.info("Post addAd");
        try
        {
            AdDto adDto = adService.addAd(createOrUpdateAdDto,image,principal);
            return ResponseEntity.ok(adDto);
        }
        catch (IOException e)
        {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExtendedAdDto> getAd(@PathVariable Integer id)
    {
        log.info("Get getAd");
        ExtendedAdDto extendedAdDto = adService.getAd(id);
        return ResponseEntity.ok().body(extendedAdDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdDto> removeAd(@PathVariable Integer id)
    {
        log.info("Delete removeAd");
        try
        {
            adService.removeAd(id);
            return ResponseEntity.ok().build();
        }
        catch (NoSuchElementException e)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AdDto> updateAd(@PathVariable Integer id,
                                          @RequestBody CreateOrUpdateAdDto createOrUpdateAdDto)
    {
        log.info("Delete removeAd");
        AdDto adDto = adService.updateAd(id, createOrUpdateAdDto);
        return ResponseEntity.ok().body(adDto);
    }

    @GetMapping("/me")
    public ResponseEntity<AdsDto> getUserAd(Principal principal)
    {
        log.info("Get getUserAd");
        AdsDto allAds = adService.getUserAd(principal);
        return ResponseEntity.ok(allAds);
    }

    @PatchMapping(value = "/{id}/image", consumes =  MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> updateAdImage(@PathVariable Integer id,
                                                @RequestParam MultipartFile image)
    {
        log.info("Patch updateAdImage");
        try {
            byte[] bytes = adService.updateAdImage(id, image);
            return ResponseEntity.ok(bytes);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }


}
