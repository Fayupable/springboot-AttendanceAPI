package com.attendance.attendance.repository;

import com.attendance.attendance.entity.UniversityDepartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartmentRepository extends JpaRepository<UniversityDepartment, Long> {

}
