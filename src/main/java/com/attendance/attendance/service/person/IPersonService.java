package com.attendance.attendance.service.person;

import com.attendance.attendance.dto.PersonDto;
import com.attendance.attendance.entity.Person;
import com.attendance.attendance.request.AddPersonRequest;

public interface IPersonService {
    Person getUserById(Long id);

    Person getUserByEmail(String email);

    Person getUserByName(String name);

    Person addPerson(AddPersonRequest person);

    Person updateUser(Person person, Long id);

    void deleteUser(Long id);

    PersonDto convertToDto(Person person);




}
