package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.security.Principal;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;


    @Override
    public boolean setPassword(NewPasswordDto newPasswordDto) {
        String currPass = newPasswordDto.getCurrentPassword();
        String newPass = newPasswordDto.getNewPassword();
        try
        {
            changePassword(currPass, newPass);
        }
        catch (NullPointerException e)
        {
            log.error("Error: %s", e);
            return false;
        }
        log.info("Change complete");
        return true;
    }

    @Override
    public UserDto getUser(Principal principal) {
        var result = userMapper.userEntityToUserDto(userRepository.findByUsername(principal.getName()));
        return result;
    }

    @Override
    public UpdateUserDto updateUser(UpdateUserDto updateUserDto, Principal principal) {
        UserEntity user = findUser(principal.getName());
        user.setFirstName(updateUserDto.getFirstName());
        user.setLastName(updateUserDto.getLastName());
        user.setPhone(updateUserDto.getPhone());
        userRepository.save(user);
        return updateUserDto;
    }

    @Override
    public String updateImage(MultipartFile fileImage, Principal principal) throws IOException {
        UserEntity user = findUser(principal.getName());
        ImageEntity image = user.getImageEntity();
        ImageEntity imageEntity;

        if (image == null) {
            imageEntity = imageService.saveImg(fileImage);
        } else {
            imageEntity = imageService.updateImg(image.getId(),fileImage);
        }

        user.setImageEntity(imageEntity);
        userRepository.save(user);
        return imageEntity.getPath();
    }

    @Override
    public byte[] getUserImage(Integer id) throws IOException {
        UserEntity user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return imageService.getByteFromFile(user.getImageEntity().getPath());
    }

    private void changePassword(String oldPass, String newPass)
    {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String name = currentUser.getName();
        UserEntity user = userRepository.findByUsername(name);

        if(!passwordEncoder.matches(oldPass, user.getPassword()))
            return;
        user.setPassword(passwordEncoder.encode(newPass));
        userRepository.save(user);
    }

    private UserEntity findUser(String name)
    {
        return userRepository.findByUsername(name);
    }
}
