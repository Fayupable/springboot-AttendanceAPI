package com.attendance.attendance.service.university;

import com.attendance.attendance.dto.UniversityDepartmentDto;
import com.attendance.attendance.entity.University;
import com.attendance.attendance.entity.UniversityDepartment;
import com.attendance.attendance.exceptions.AlreadyExistsException;
import com.attendance.attendance.repository.IDepartmentRepository;
import com.attendance.attendance.repository.IUniversityRepository;
import com.attendance.attendance.request.university.department.AddUniversityDepartmentRequest;
import com.attendance.attendance.request.university.department.UpdateUniversityDepartmentRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityDepartmentService implements IUniversityDepartmentService {

    private final IDepartmentRepository departmentRepository;
    private final IUniversityRepository universityRepository;
    private final ModelMapper modelMapper;

    @Override
    public UniversityDepartment getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Department not found"));
    }

    @Override
    public List<UniversityDepartment> getAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public List<UniversityDepartment> getDepartmentByName(String name) {
        return departmentRepository.findByDepartmentNameContaining(name);
    }

    @Override
    public UniversityDepartment addDepartment(AddUniversityDepartmentRequest department) {
        validateDepartmentName(department.getDepartmentName(), department.getUniversityId());

        var university = findUniversityById(department.getUniversityId());

        UniversityDepartment universityDepartment = createNewDepartment(department, university);

        return saveDepartment(universityDepartment);
    }

    private void validateDepartmentName(String departmentName, Long universityId) {
        University university = findUniversityById(universityId);
        if (departmentRepository.existsByDepartmentNameAndUniversity(departmentName, university)) {
            throw new AlreadyExistsException("Department with name '" + departmentName + "' already exists in university: " + university.getUniversityName());
        }
    }

    private University findUniversityById(Long universityId) {
        return universityRepository.findById(universityId)
                .orElseThrow(() -> new IllegalArgumentException("University not found with ID: " + universityId));
    }

    private UniversityDepartment createNewDepartment(AddUniversityDepartmentRequest department, University university) {
        UniversityDepartment universityDepartment = new UniversityDepartment();
        universityDepartment.setDepartmentName(department.getDepartmentName());
        universityDepartment.setUniversity(university);
        return universityDepartment;
    }

    private UniversityDepartment saveDepartment(UniversityDepartment department) {
        return departmentRepository.save(department);
    }

    @Override
    public UniversityDepartment updateDepartment(UpdateUniversityDepartmentRequest request, Long id) {
        return departmentRepository.findById(id)
                .map(existingDepartment -> {
                    existingDepartment.setDepartmentName(request.getDepartmentName());
                    return departmentRepository.save(existingDepartment);
                }).orElseThrow(() -> new IllegalArgumentException("Department not found"));
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.findById(id).ifPresentOrElse(departmentRepository::delete, () -> {
            throw new IllegalArgumentException("Department not found");
        });
    }

    @Override
    public boolean existsByDepartmentName(String name) {
        return departmentRepository.existsByDepartmentName(name);
    }

    public UniversityDepartmentDto convertToDto(UniversityDepartment department) {
        return modelMapper.map(department, UniversityDepartmentDto.class);
    }

    public List<UniversityDepartmentDto> convertToDtoList(List<UniversityDepartment> departments) {
        return departments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
