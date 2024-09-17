package com.attendance.attendance.service.university;

import com.attendance.attendance.dto.UniversityDto;
import com.attendance.attendance.entity.Student;
import com.attendance.attendance.entity.University;
import com.attendance.attendance.entity.UniversityDepartment;
import com.attendance.attendance.enums.Role;
import com.attendance.attendance.exceptions.AlreadyExistsException;
import com.attendance.attendance.exceptions.NotFoundException;
import com.attendance.attendance.repository.IDepartmentRepository;
import com.attendance.attendance.repository.IStudentRepository;
import com.attendance.attendance.repository.IUniversityRepository;
import com.attendance.attendance.request.university.AddUniversityDepartmentRequest;
import com.attendance.attendance.request.university.AddUniversityRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityService implements IUniversityService {

    private final IUniversityRepository universityRepository;
    private final IStudentRepository studentRepository;
    private final IDepartmentRepository departmentRepository;
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
        return Optional.of(request)
                .filter(university -> !universityRepository.existsByUniversityName(request.getUniversityName()))
                .map(req -> {
                    University university = new University();
                    university.setUniversityName(request.getUniversityName());
                    university.setLocation(request.getLocation());
                    addDepartments(request, university);
                    return universityRepository.save(university);
                }).orElseThrow(() -> new AlreadyExistsException("Oops" + request.getUniversityName() + " already exists"));

    }

    private void addDepartments(AddUniversityRequest request, University university) {
        List<AddUniversityDepartmentRequest> departmentRequests = request.getDepartments();

        if (departmentRequests != null && !departmentRequests.isEmpty()) {
            List<UniversityDepartment> departments = departmentRequests.stream()
                    .map(deptRequest -> {
                        UniversityDepartment department = new UniversityDepartment();
                        department.setDepartmentName(deptRequest.getDepartmentName());
                        department.setUniversity(university);
                        return department;
                    })
                    .collect(Collectors.toList());

            university.setDepartments(departments);
        }
    }


    @Override
    public University updateUniversity(University university, Long id) {
        return null;
    }

    @Override
    public void deleteUniversity(Long id) {

        universityRepository.findById(id).ifPresentOrElse(universityRepository::delete, () -> {
            throw new NotFoundException("University not found");
        });
    }


    @Override
    public boolean existsByUniversityName(String name) {
        return false;
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
