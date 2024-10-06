package com.attendance.attendance.repository;

import com.attendance.attendance.entity.University;
import com.attendance.attendance.entity.UniversityDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDepartmentRepository extends JpaRepository<UniversityDepartment, Long> {



    List<UniversityDepartment> findByDepartmentNameContaining(String name);

    boolean existsByDepartmentName(String name);


    boolean existsByDepartmentNameAndUniversity(String departmentName, University university);

}
