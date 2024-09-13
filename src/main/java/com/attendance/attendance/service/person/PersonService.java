package com.attendance.attendance.service.person;

import com.attendance.attendance.entity.Person;
import com.attendance.attendance.entity.Student;
import com.attendance.attendance.entity.Teacher;
import com.attendance.attendance.enums.Role;
import com.attendance.attendance.repository.IPersonRepository;
import com.attendance.attendance.request.AddPersonRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService implements IPersonService {

    private final IPersonRepository personRepository;
    private final ModelMapper modelMapper;

    @Override
    public Person getUserById(Long id) {
        return null;
    }

    @Override
    public Person getUserByEmail(String email) {
        return null;
    }

    @Override
    public Person getUserByName(String name) {
        return null;
    }

    @Override
    public Person addPerson(AddPersonRequest person) {
        Person personEntity = modelMapper.map(person, Person.class);
        if (person.getRole().equals(Role.STUDENT)) {
            Student student = modelMapper.map(person, Student.class);
            personRepository.save(student);
        } else if (person.getRole().equals(Role.TEACHER)) {
            Teacher teacher = modelMapper.map(person, Teacher.class);
            personRepository.save(teacher);
        }
        return personRepository.save(personEntity);
    }

    @Override
    public Person updateUser(Person person, Long id) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
