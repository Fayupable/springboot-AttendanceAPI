package com.attendance.attendance.service.university;

import com.attendance.attendance.dto.UniversityDto;
import com.attendance.attendance.entity.University;
import com.attendance.attendance.entity.UniversityDepartment;
import com.attendance.attendance.exceptions.AlreadyExistsException;
import com.attendance.attendance.repository.IUniversityRepository;
import com.attendance.attendance.request.university.AddUniversityDepartmentRequest;
import com.attendance.attendance.request.university.AddUniversityRequest;
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
    public University getUniversityById(Long id) {
        return null;
    }

    @Override
    public University getUniversityByName(String name) {
        return null;
    }


    /*
    return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    User user = new User();
                    user.setEmail(request.getEmail());
                    user.setPassword(passwordEncoder.encode(request.getPassword()));
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    return userRepository.save(user);
                }).orElseThrow(() -> new AlreadyExistsException("Oops" + request.getEmail() + " already exists"));
     */
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

    }

    @Override
    public boolean existsByUniversityName(String name) {
        return false;
    }

    @Override
    public UniversityDto convertToDto(University university) {
        return modelMapper.map(university, UniversityDto.class);
    }
}
