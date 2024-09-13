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
    public Person addPerson(AddPersonRequest request) {
        if (personRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Person with this email already exists");
        }

        Person person = new Person();
        person.setEmail(request.getEmail());
        person.setPassword(request.getPassword());
        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setDateOfBirth(request.getDateOfBirth());
        person.setRole(request.getRole());

        return personRepository.save(person);
    }

    @Override
    public Person updateUser(Person person, Long id) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
