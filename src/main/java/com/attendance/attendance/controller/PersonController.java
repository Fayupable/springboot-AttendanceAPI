package com.attendance.attendance.controller;

import com.attendance.attendance.dto.PersonDto;
import com.attendance.attendance.entity.Person;
import com.attendance.attendance.request.person.AddPersonRequest;
import com.attendance.attendance.request.person.UpdatePersonRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.person.IPersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/person")
@Tag(name = "Person")
public class PersonController {
    private final IPersonService personService;

    @Operation(
            summary = "Get person by ID",
            description = "Retrieve person by their ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Person retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Person not found")
            }
    )
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

    @Operation(
            summary = "Get all persons",
            description = "Retrieve all persons",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Persons retrieved successfully")
            }
    )
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllPerson() {
        List<Person> person = personService.getAllPerson();
        List<PersonDto> personDto = personService.getConvertedPerson(person);
        return ResponseEntity.ok(new ApiResponse("Persons retrieved successfully", personDto));
    }

    @Operation(
            summary = "Get person by name",
            description = "Retrieve person by their name",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Person retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Person not found")
            }
    )
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

    @Operation(
            summary = "Get person by email",
            description = "Retrieve person by their email",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Person retrieved successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Person not found")
            }
    )
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

    @Operation(
            summary = "Add person",
            description = "Add a new person",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Person created successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Conflict")
            }
    )
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

    @Operation(
            summary = "Update person",
            description = "Update an existing person",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Person updated successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Conflict")
            }
    )
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

    @Operation(
            summary = "Delete person",
            description = "Delete a person by their ID",
            responses = {
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Person deleted successfully"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Conflict")
            }
    )
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