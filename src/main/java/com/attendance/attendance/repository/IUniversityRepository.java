package com.attendance.attendance.repository;

import com.attendance.attendance.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUniversityRepository extends JpaRepository<University, Long> {
    boolean existsByUniversityName(String name);

    University findByUniversityName(String name);


    List<University> findByUniversityNameContaining(String name);
}
