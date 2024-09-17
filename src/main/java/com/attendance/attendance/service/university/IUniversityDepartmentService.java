package com.attendance.attendance.service.university;

import com.attendance.attendance.entity.UniversityDepartment;

import java.util.List;

public interface IUniversityDepartmentService {

    UniversityDepartment getDepartmentById(Long id);

    List<UniversityDepartment> getAllDepartment();

    List<UniversityDepartment> getDepartmentByName(String name);

    UniversityDepartment addDepartment(UniversityDepartment department);

    UniversityDepartment updateDepartment(UniversityDepartment department, Long id);

    void deleteDepartment(Long id);

    boolean existsByDepartmentName(String name);




}
