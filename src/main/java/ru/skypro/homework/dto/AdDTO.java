package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdDTO {
    private int author;
    private String image;
    private long pk;
    private int price;
    private String title;
    private String description;
}
