package ru.skypro.homework.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AdRepository;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class RoleAccess {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final AdRepository adRepository;

    public boolean adminOwnerComment(Integer commentId, Authentication authentication)
    {
        UserEntity userEntity = userRepository.findByUsername(authentication.getName());
        CommentEntity commentEntity = commentRepository.findById(commentId).get();
        return userEntity.getRole().name().equals(Role.ADMIN.name()) || userEntity.getId() == commentEntity.getUser().getId();
    }

    public boolean adminOwnerAd(Integer adId, Authentication authentication)
    {
        UserEntity userEntity = userRepository.findByUsername(authentication.getName());
        AdEntity adEntity = adRepository.findById(adId).get();
        return userEntity.getRole().name().equals(Role.ADMIN.name()) || userEntity.getId() == adEntity.getUsersId().getId();
    }
}
