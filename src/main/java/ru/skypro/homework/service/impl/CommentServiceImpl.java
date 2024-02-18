package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AdService;
import ru.skypro.homework.service.CommentService;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    CommentMapper commentMapper = Mappers.getMapper(CommentMapper.class);
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final AdService adService;

    @Override
    public CommentsDto getAdComments(Integer id) {
        log.info("Run CommentServiceImpl getAdComments");
        AdEntity adEntity = adService.findById(id);
        List<CommentEntity> commentEntityList = adEntity.getComments();
        List<CommentDto> commentDtoList = commentMapper.listCommentEntityToListCommentDto(commentEntityList);
        return new CommentsDto(commentDtoList.size(), commentDtoList);
    }

    @Override
    public CommentDto addComment(Integer id, CreateOrUpdateCommentDto createOrUpdateCommentDto, Principal principal) {
        log.info("Run CommentServiceImpl addComment");
        AdEntity adEntity = adService.findById(id);
        UserEntity userEntity = userRepository.findByUsername(principal.getName());
        CommentEntity commentEntity = commentMapper.createOrUpdateCommentDtoAdEntityToCommentEntity(createOrUpdateCommentDto, adEntity, userEntity);
        commentRepository.save(commentEntity);
        return commentMapper.commentsEntityToCommentDto(commentEntity);
    }

    @Override
    public boolean removeComment(Integer id) {
        log.info("Run CommentServiceImpl removeComment");
        try
        {
            commentRepository.deleteById(id);
            return true;
        }
        catch (RuntimeException e) {
            return false;
        }
    }

    @Override
    public CommentDto updateComment(Integer id, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        log.info("Run CommentServiceImpl updateComment");
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(NoSuchElementException::new);
        commentEntity.setText(createOrUpdateCommentDto.getText());
        commentRepository.save(commentEntity);
        return commentMapper.commentsEntityToCommentDto(commentEntity);
    }
}
