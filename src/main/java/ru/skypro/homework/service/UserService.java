package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

import java.security.Principal;

public interface UserService {
    boolean setPassword(NewPasswordDto newPasswordDto);
    UserDto getUser(Principal principal);
    UpdateUserDto updateUser(UpdateUserDto updateUserDto, Principal principal);
    String updateImage();
}
