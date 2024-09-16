package com.attendance.attendance.service.student;

import com.attendance.attendance.dto.StudentDto;
import com.attendance.attendance.entity.Student;
import com.attendance.attendance.entity.University;
import com.attendance.attendance.entity.UniversityDepartment;
import com.attendance.attendance.enums.Role;
import com.attendance.attendance.exceptions.AlreadyExistsException;
import com.attendance.attendance.exceptions.ResourceNotFoundException;
import com.attendance.attendance.repository.IDepartmentRepository;
import com.attendance.attendance.repository.IPersonRepository;
import com.attendance.attendance.repository.IStudentRepository;
import com.attendance.attendance.repository.IUniversityRepository;

import com.attendance.attendance.request.student.AddStudentRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    private final IStudentRepository studentRepository;
    private final IPersonRepository personRepository;
    private final IUniversityRepository universityRepository;
    private final IDepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public Student getStudentByEmail(String email) {
        return null;
    }

    @Override
    public Student getStudentByName(String name) {
        return null;
    }

    @Override
    public Student getStudentById(Long id) {
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
    public Student addStudent(AddStudentRequest request) {
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException("Oops! " + request.getEmail() + " already exists");
        }
        Student student = new Student();
        student.setStudentNumber(request.getStudentNumber());
        student.setEmail(request.getEmail());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setPassword(request.getPassword());
        student.setRole(Role.STUDENT);
        University university = universityRepository.findById(request.getUniversityId())
                .orElseThrow(() -> new ResourceNotFoundException("University not found with ID: " + request.getUniversityId()));
        student.setUniversity(university);

        UniversityDepartment department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + request.getDepartmentId()));
        student.setDepartment(department);

        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Student student, Long id) {
        return null;
    }

    @Override
    public void deleteStudent(Long id) {

    }

    @Override
    public StudentDto convertToDto(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }
}
