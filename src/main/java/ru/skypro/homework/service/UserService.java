package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

import java.io.IOException;
import java.security.Principal;

public interface UserService {
    boolean setPassword(NewPasswordDto newPasswordDto);
    UserDto getUser(Principal principal);
    UpdateUserDto updateUser(UpdateUserDto updateUserDto, Principal principal);
    String updateImage(MultipartFile image, Principal principal) throws IOException;

    byte[] getUserImage(Integer id) throws IOException;
}
