package com.attendance.attendance.service.person;

import com.attendance.attendance.entity.Person;
import com.attendance.attendance.entity.Student;
import com.attendance.attendance.entity.Teacher;
import com.attendance.attendance.enums.Role;
import com.attendance.attendance.exceptions.AlreadyExistsException;
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
    public Person addPerson(AddPersonRequest request) {
        return Optional.of(request)
                .filter(person -> !personRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    Person person = new Person();
                    person.setEmail(request.getEmail());
                    person.setFirstName(request.getFirstName());
                    person.setLastName(request.getLastName());
                    person.setPassword(request.getPassword());
                    person.setRole(Role.valueOf(String.valueOf(request.getRole())));
                    person.setDateOfBirth(request.getDateOfBirth());
                    return personRepository.save(person);
                }).orElseThrow(() -> new AlreadyExistsException("Oops" + request.getEmail() + " already exists"));
    }

    @Override
    public Person updateUser(Person person, Long id) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
