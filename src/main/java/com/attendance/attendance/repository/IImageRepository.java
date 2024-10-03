package com.attendance.attendance.repository;

import com.attendance.attendance.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByPersonId(Long id);
}
