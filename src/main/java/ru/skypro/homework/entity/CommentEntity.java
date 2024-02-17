package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "comments")
public class CommentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int pk;

    @Column(name = "created_at")
    private Date createdAt = Date.valueOf(LocalDate.now());
    private String text;

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "ad", referencedColumnName = "id")
    private AdEntity ad;
}
