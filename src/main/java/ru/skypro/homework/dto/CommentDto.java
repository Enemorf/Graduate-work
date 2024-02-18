package ru.skypro.homework.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class CommentDto {
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private Date createdAt;
    private Integer pk;
    private String text;
}
