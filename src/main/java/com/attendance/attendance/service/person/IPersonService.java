package com.attendance.attendance.service.person;

import com.attendance.attendance.dto.PersonDto;
import com.attendance.attendance.entity.Person;
import com.attendance.attendance.request.person.AddPersonRequest;
import com.attendance.attendance.request.person.UpdatePersonRequest;

import java.util.List;

public interface IPersonService {
    Person getUserById(Long id);

    List<Person> getAllPerson();

    List<Person> getUserByEmail(String email);

    List<Person> getUserByName(String name);

    Person addPerson(AddPersonRequest person);

    Person updateUser(UpdatePersonRequest person, Long id);

    void deleteUser(Long id);

    List<PersonDto> getConvertedPerson(List<Person> person);

    PersonDto convertToDto(Person person);


}
