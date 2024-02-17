package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

import java.security.Principal;

public interface CommentService {
    CommentsDto getAdComments(Integer id);
    CommentDto addComment(Integer id, CreateOrUpdateCommentDto createOrUpdateCommentDto, Principal principal);
    boolean removeComment(Integer id, Integer commentId);
    CommentDto updateComment(Integer id, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto);
}
