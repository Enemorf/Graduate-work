package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.repository.CommentRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.CommentService;

import java.security.Principal;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository)
    {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CommentsDto getAdComments(Integer id) {
        log.info("Run CommentServiceImpl getAdComments");
        return null;
    }

    @Override
    public CommentDto addComment(Integer id, CreateOrUpdateCommentDto createOrUpdateCommentDto, Principal principal) {
        log.info("Run CommentServiceImpl addComment");

        return null;
    }

    @Override
    public boolean removeComment(Integer id, Integer commentId) {
        log.info("Run CommentServiceImpl removeComment");
        return false;
    }

    @Override
    public CommentDto updateComment(Integer id, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        log.info("Run CommentServiceImpl updateComment");
        return null;
    }
}
