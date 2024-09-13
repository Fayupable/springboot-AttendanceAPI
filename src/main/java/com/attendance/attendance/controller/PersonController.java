package com.attendance.attendance.controller;

import com.attendance.attendance.entity.Person;
import com.attendance.attendance.request.AddPersonRequest;
import com.attendance.attendance.response.ApiResponse;
import com.attendance.attendance.service.person.IPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/person")
public class PersonController {
    private final IPersonService personService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createPerson(@RequestBody AddPersonRequest request) {
        try {
            Person createdPerson = personService.addPerson(request);
            return ResponseEntity.ok(new ApiResponse("Person created successfully", createdPerson));
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
