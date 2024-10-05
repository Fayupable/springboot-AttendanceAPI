package com.attendance.attendance.service.teacher;

import com.attendance.attendance.dto.TeacherDto;
import com.attendance.attendance.entity.Teacher;
import com.attendance.attendance.entity.University;
import com.attendance.attendance.entity.UniversityDepartment;
import com.attendance.attendance.exceptions.AlreadyExistsException;
import com.attendance.attendance.exceptions.ResourceNotFoundException;
import com.attendance.attendance.repository.IDepartmentRepository;
import com.attendance.attendance.repository.ITeacherRepository;
import com.attendance.attendance.repository.IUniversityRepository;
import com.attendance.attendance.request.teacher.AddTeacherRequest;
import com.attendance.attendance.request.teacher.UpdateTeacherRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService implements ITeacherService {
    private final ITeacherRepository teacherRepository;
    private final IUniversityRepository universityRepository;
    private final IDepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Teacher> getTeacherByEmail(String email) {
        return teacherRepository.findByEmailContaining(email);
    }

    @Override
    public List<Teacher> getTeacherByName(String name) {
        return teacherRepository.findByFirstNameContaining(name);
    }

    @Override
    public List<Teacher> getTeacherByUniversityId(Long universityId) {
        return teacherRepository.findTeacherByUniversity_UniversityId(universityId);
    }

    @Override
    public List<Teacher> getTeacherByDepartmentId(Long departmentId) {
        return teacherRepository.findTeacherByDepartment_DepartmentId(departmentId);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public Teacher addTeacher(AddTeacherRequest request) {
        return Optional.of(request)
                .map(req -> {
                    Teacher teacher = new Teacher();
                    createTeacher(request, teacher);
                    return teacherRepository.save(teacher);
                }).orElseThrow(() -> new RuntimeException("Teacher not created"));


    }

    private void createTeacher(AddTeacherRequest request, Teacher teacher) {
        teacher.setEmail(request.getEmail());
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setDateOfBirth(request.getDateOfBirth());
        teacher.setPassword(request.getPassword());
        checkDepartment(request, teacher);
        checkUniversity(request, teacher);
        checkEmail(request, teacher);

    }

    private void checkEmail(AddTeacherRequest request, Teacher teacher) {
        if (teacherRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException("Oops! " + request.getEmail() + " already exists");
        }
    }

    private void checkUniversity(AddTeacherRequest request, Teacher teacher) {
        University university = universityRepository.findById(request.getUniversityId())
                .orElseThrow(() -> new ResourceNotFoundException("University not found with ID: " + request.getUniversityId()));
        teacher.setUniversity(university);
    }

    private void checkDepartment(AddTeacherRequest request, Teacher teacher) {
        UniversityDepartment department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + request.getDepartmentId()));
        teacher.setDepartment(department);
    }

    @Override
    public Teacher updateTeacher(UpdateTeacherRequest teacher, Long id) {
        return teacherRepository.findById(id)
                .map(existingTeacher -> {
                    Teacher updatedTeacher = updateExistingTeacher(existingTeacher, teacher);
                    return teacherRepository.save(updatedTeacher);
                }).orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
    }

    private Teacher updateExistingTeacher(Teacher existingTeacher, UpdateTeacherRequest request) {
        existingTeacher.setFirstName(request.getFirstName());
        existingTeacher.setLastName(request.getLastName());
        existingTeacher.setEmail(request.getEmail());
        existingTeacher.setPassword(request.getPassword());
        existingTeacher.setDateOfBirth(request.getDateOfBirth());
        checkDepartment(request, existingTeacher);
        checkUniversity(request, existingTeacher);
        checkEmail(request, existingTeacher);


        return existingTeacher;
    }

    private void checkEmail(UpdateTeacherRequest request, Teacher teacher) {
        if (teacherRepository.existsByEmailAndIdNot(request.getEmail(), teacher.getId())) {
            throw new AlreadyExistsException("Oops! " + request.getEmail() + " already exists");
        }
    }

    private void checkUniversity(UpdateTeacherRequest request, Teacher teacher) {
        University university = universityRepository.findById(request.getUniversityId())
                .orElseThrow(() -> new ResourceNotFoundException("University not found with ID: " + request.getUniversityId()));
        teacher.setUniversity(university);
    }

    private void checkDepartment(UpdateTeacherRequest request, Teacher teacher) {
        UniversityDepartment department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + request.getDepartmentId()));
        teacher.setDepartment(department);
    }


    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.findById(id).ifPresentOrElse(teacherRepository::delete, () -> {
            throw new RuntimeException("Teacher not found");
        });

    }

    @Override
    public TeacherDto convertToDto(Teacher teacher) {
        return modelMapper.map(teacher, TeacherDto.class);
    }

    @Override
    public List<TeacherDto> convertToDtoList(List<Teacher> teachers) {
        return teachers.stream()
                .map(this::convertToDto)
                .toList();
    }
}
