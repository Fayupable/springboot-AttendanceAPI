package com.attendance.attendance.service.university;

import com.attendance.attendance.dto.UniversityDepartmentDto;
import com.attendance.attendance.entity.UniversityDepartment;
import com.attendance.attendance.request.university.AddUniversityDepartmentRequest;
import com.attendance.attendance.request.university.UpdateUniversityDepartmentRequest;

import java.util.List;

public interface IUniversityDepartmentService {

    UniversityDepartment getDepartmentById(Long id);

    List<UniversityDepartment> getAllDepartment();

    List<UniversityDepartment> getDepartmentByName(String name);

    UniversityDepartment addDepartment(AddUniversityDepartmentRequest request);

    UniversityDepartment updateDepartment(UpdateUniversityDepartmentRequest department, Long id);

    void deleteDepartment(Long id);

    boolean existsByDepartmentName(String name);


    UniversityDepartmentDto convertToDto(UniversityDepartment theDepartment);

    List<UniversityDepartmentDto> convertToDtoList(List<UniversityDepartment> theDepartments);
}
