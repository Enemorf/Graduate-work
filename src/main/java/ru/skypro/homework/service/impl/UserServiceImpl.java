package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

import java.security.Principal;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Override
    public boolean setPassword(NewPasswordDto newPasswordDto) {
        return false;
    }

    @Override
    public UserDto getUser(Principal principal) {
        return null;
    }

    @Override
    public UpdateUserDto updateUser(UpdateUserDto updateUserDto, Principal principal) {
        return null;
    }

    @Override
    public String updateImage() {
        return null;
    }
}
