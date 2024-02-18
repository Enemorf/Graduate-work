package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.UserEntity;

import java.io.IOException;

public interface UserService {
    UserDTO updateUser(UserDTO userDTO, Authentication authentication);
    UserDTO getAuthorizedUserDto(Authentication authentication);
    UserEntity getAuthorizedUser(Authentication authentication);
    void changePassword(NewPasswordDTO newPasswordDTO, Authentication authentication);
    void updateUserImage(MultipartFile image, Authentication authentication) throws IOException;
    byte[] getUserPhoto(Integer userId) throws IOException;

}