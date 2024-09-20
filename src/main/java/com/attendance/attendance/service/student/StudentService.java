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
import com.attendance.attendance.request.student.UpdateStudentRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {
    private final IStudentRepository studentRepository;
    private final IUniversityRepository universityRepository;
    private final IDepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudentByEmail(String email) {
        return studentRepository.findByEmailContaining(email);
    }

    @Override
    public List<Student> getStudentByName(String name) {
        return studentRepository.findStudentByFirstNameContaining(name);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    /*
      @Override
    public Person addPerson(AddPersonRequest request) {
        return Optional.of(request)
                .filter(person -> !personRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    Person person = createPerson(request);
                    return personRepository.save(person);
                }).orElseThrow(() -> new AlreadyExistsException("Oops" + request.getEmail() + " already exists"));
    }
     */

    @Override
    public Student addStudent(AddStudentRequest request) {
        return Optional.of(request)
                .map(req -> {
                    Student student = new Student();
                    createStudent(request, student);
                    return studentRepository.save(student);
                }).orElseThrow(() -> new AlreadyExistsException("Student already exists"));
    }

    private void createStudent(AddStudentRequest request, Student student) {
        setStudentFields(request, student);
        checkMail(request.getEmail());
        checkStudentNumber(request.getStudentNumber());
        checkUniversity(request, student);
        checkDepartment(request, student);

    }
    private void setStudentFields(AddStudentRequest request, Student student){
        student.setStudentNumber(request.getStudentNumber());
        student.setEmail(request.getEmail());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setDateOfBirth(request.getDateOfBirth());
        student.setPassword(request.getPassword());
        student.setRole(Role.STUDENT);
    }

    private void checkUniversity(AddStudentRequest request, Student student) {
        University university = universityRepository.findById(request.getUniversityId())
                .orElseThrow(() -> new ResourceNotFoundException("University not found with ID: " + request.getUniversityId()));
        student.setUniversity(university);
    }

    private void checkDepartment(AddStudentRequest request, Student student) {
        UniversityDepartment department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + request.getDepartmentId()));
        student.setDepartment(department);
    }

    private void checkMail(String email) {
        if (studentRepository.existsByEmail(email)) {
            throw new AlreadyExistsException("Student mail already exists");
        }
    }

    private void checkStudentNumber(String studentNumber) {
        if (studentRepository.existsByStudentNumber(studentNumber)) {
            throw new AlreadyExistsException("Student number already exists");
        }
    }

    @Override
    public Student updateStudent(UpdateStudentRequest request, Long id) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    Student updatedStudent = updateExistingStudent(existingStudent, request);
                    return studentRepository.save(updatedStudent);
                }).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    private Student updateExistingStudent(Student existingStudent, UpdateStudentRequest request) {
        existingStudent.setFirstName(request.getFirstName());
        existingStudent.setLastName(request.getLastName());
        existingStudent.setEmail(request.getEmail());
        existingStudent.setPassword(request.getPassword());
        existingStudent.setDateOfBirth(request.getDateOfBirth());
        return existingStudent;
    }


    @Override
    public void deleteStudent(Long id) {
        studentRepository.findById(id).ifPresentOrElse(studentRepository::delete,
                () -> {
                    throw new ResourceNotFoundException("Student not found");
                });
    }

    @Override
    public StudentDto convertToDto(Student student) {
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public List<StudentDto> convertToDtoList(List<Student> students) {
        return students.stream()
                .map(this::convertToDto)
                .toList();
    }
}
