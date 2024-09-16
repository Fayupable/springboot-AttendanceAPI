package com.attendance.attendance.controller;

import com.attendance.attendance.dto.PersonDto;
import com.attendance.attendance.entity.Person;
import com.attendance.attendance.request.person.AddPersonRequest;
import com.attendance.attendance.request.person.UpdatePersonRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.person.IPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/person")
public class PersonController {
    private final IPersonService personService;

    @GetMapping("/{personId}")
    public ResponseEntity<ApiResponse> getPersonById(@PathVariable Long personId) {
        try {
            Person person = personService.getUserById(personId);
            PersonDto personDto = personService.convertToDto(person);
            return ResponseEntity.ok(new ApiResponse("Person retrieved successfully", personDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllPerson() {
        List<Person> person = personService.getAllPerson();
        List<PersonDto> personDto = personService.getConvertedPerson(person);
        return ResponseEntity.ok(new ApiResponse("Person retrieved successfully", personDto));
    }

    @GetMapping("/personName/{personName}")
    public ResponseEntity<ApiResponse> getPersonByName(@PathVariable String personName) {
        try {
            List<Person> person = personService.getUserByName(personName);
            List<PersonDto> personDto = personService.getConvertedPerson(person);
            return ResponseEntity.ok(new ApiResponse("Person retrieved successfully", personDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/personEmail/{personEmail}")
    public ResponseEntity<ApiResponse> getPersonByEmail(@PathVariable String personEmail) {
        try {
            List<Person> person = personService.getUserByEmail(personEmail);
            List<PersonDto> personDto = personService.getConvertedPerson(person);
            return ResponseEntity.ok(new ApiResponse("Person retrieved successfully", personDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PostMapping("/add")
    @Transactional
    public ResponseEntity<ApiResponse> createPerson(@RequestBody AddPersonRequest request) {
        try {
            Person createdPerson = personService.addPerson(request);
            PersonDto personDto = personService.convertToDto(createdPerson);
            return ResponseEntity.ok(new ApiResponse("Person created successfully", personDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{personId}/update")
    @Transactional
    public ResponseEntity<ApiResponse> updatePerson(@RequestBody UpdatePersonRequest request, @PathVariable Long personId) {
        try {
            Person updatedPerson = personService.updateUser(request, personId);
            PersonDto personDto = personService.convertToDto(updatedPerson);
            return ResponseEntity.ok(new ApiResponse("Person updated successfully", personDto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{personId}/delete")
    @Transactional
    public ResponseEntity<ApiResponse> deletePerson(@PathVariable Long personId) {
        try {
            personService.deleteUser(personId);
            return ResponseEntity.ok(new ApiResponse("Person deleted successfully", null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
