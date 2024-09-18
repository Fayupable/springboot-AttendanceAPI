package com.attendance.attendance.service.university;

import com.attendance.attendance.dto.UniversityDto;
import com.attendance.attendance.entity.University;
import com.attendance.attendance.entity.UniversityDepartment;
import com.attendance.attendance.exceptions.AlreadyExistsException;
import com.attendance.attendance.exceptions.NotFoundException;
import com.attendance.attendance.repository.IUniversityRepository;
import com.attendance.attendance.request.university.department.AddUniversityDepartmentRequest;
import com.attendance.attendance.request.university.university.AddUniversityRequest;
import com.attendance.attendance.request.university.university.UpdateUniversityRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityService implements IUniversityService {

    private final IUniversityRepository universityRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<University> getAllUniversity() {
        return universityRepository.findAll();
    }

    @Override
    public University getUniversityById(Long id) {
        return universityRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("University not found"));
    }

    @Override
    public List<University> getUniversityByName(String name) {
        return universityRepository.findByUniversityNameContaining(name);
    }


    @Override
    public University addUniversity(AddUniversityRequest request) {
        validateUniversityName(request.getUniversityName());
        University university = createUniversityFromRequest(request);
        addDepartmentsToUniversity(request, university);
        return saveUniversity(university);
    }

    private void validateUniversityName(String universityName) {
        if (existsByUniversityName(universityName)) {
            throw new AlreadyExistsException("Oops, " + universityName + " already exists");
        }
    }

    private University createUniversityFromRequest(AddUniversityRequest request) {
        University university = new University();
        university.setUniversityName(request.getUniversityName());
        university.setLocation(request.getLocation());
        return university;
    }

    private void addDepartmentsToUniversity(AddUniversityRequest request, University university) {
        List<AddUniversityDepartmentRequest> departmentRequests = request.getDepartments();
        if (departmentRequests != null && !departmentRequests.isEmpty()) {
            List<UniversityDepartment> departments = departmentRequests.stream()
                    .map(deptRequest -> createDepartmentFromRequest(deptRequest, university))
                    .collect(Collectors.toList());
            university.setDepartments(departments);
        }
    }

    private UniversityDepartment createDepartmentFromRequest(AddUniversityDepartmentRequest deptRequest, University university) {
        UniversityDepartment department = new UniversityDepartment();
        department.setDepartmentName(deptRequest.getDepartmentName());
        department.setUniversity(university);
        return department;
    }

    private University saveUniversity(University university) {
        return universityRepository.save(university);
    }

    @Override
    public University updateUniversity(UpdateUniversityRequest request, Long id) {
        return universityRepository.findById(id)
                .map(existingUniversity -> {
                    existingUniversity.setUniversityName(request.getUniversityName());
                    existingUniversity.setLocation(request.getLocation());
                    return universityRepository.save(existingUniversity);
                }).orElseThrow(() -> new NotFoundException("University not found"));
    }

    @Override
    public void deleteUniversity(Long id) {

        universityRepository.findById(id).ifPresentOrElse(universityRepository::delete, () -> {
            throw new NotFoundException("University not found");
        });
    }


    @Override
    public boolean existsByUniversityName(String name) {
        return universityRepository.existsByUniversityName(name);
    }

    @Override
    public UniversityDto convertToDto(University university) {
        return modelMapper.map(university, UniversityDto.class);
    }

    @Override
    public List<UniversityDto> getConvertedUniversity(List<University> universities) {
        return universities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}
