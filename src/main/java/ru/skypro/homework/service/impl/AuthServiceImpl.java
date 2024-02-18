package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    UserMapper userMapper = Mappers.getMapper(UserMapper.class);
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public boolean login(String userName, String password) {
        UserEntity user = userRepository.findByUsername(userName);
        if(user != null)
            return encoder.matches(password,user.getPassword());
        return false;
    }

    @Override
    public boolean register(RegisterDto registerDto) {
        UserEntity user = userMapper.registerDtoToUserEntity(registerDto);
        UserEntity findUser = userRepository.findByUsername(user.getUsername());

        if(findUser != null)
            return false;

        String encode = encoder.encode(registerDto.getPassword());
        user.setEmail(registerDto.getUsername());
        user.setPassword(encode);
        userRepository.save(user);
        return true;
    }

}
