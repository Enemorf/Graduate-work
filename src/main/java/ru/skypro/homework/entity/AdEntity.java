package ru.skypro.homework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "ads")
public class AdEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private Integer price;
    private String title;
    private String description;

    @OneToOne
    @JoinColumn(name = "image")
    private ImageEntity imageEntity;

    @ManyToOne
    @JoinColumn(name = "author",referencedColumnName = "id")
    private UserEntity usersId;

    @OneToMany(mappedBy = "ad")
    @JsonIgnore
    private List<CommentEntity> comments;
}
