package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommentsDto {
    private final Integer count;
    private final List<CommentDto> results;
}
