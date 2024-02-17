package ru.skypro.homework.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.skypro.homework.entity.ImageEntity;

public interface ImagesRepository extends JpaRepository<ImageEntity, Integer> {
}
