package com.attendance.attendance.service.person;

import com.attendance.attendance.dto.ImageDto;
import com.attendance.attendance.dto.PersonDto;
import com.attendance.attendance.entity.Image;
import com.attendance.attendance.entity.Person;
import com.attendance.attendance.exceptions.AlreadyExistsException;
import com.attendance.attendance.repository.IImageRepository;
import com.attendance.attendance.repository.IPersonRepository;
import com.attendance.attendance.request.log.LogRequest;
import com.attendance.attendance.request.person.AddPersonRequest;
import com.attendance.attendance.request.person.UpdatePersonRequest;
import com.attendance.attendance.security.user.AttendanceUserDetails;
import com.attendance.attendance.service.log.ILogService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService implements IPersonService {

    private final IPersonRepository personRepository;
    private final IImageRepository imageRepository;
    private final ModelMapper modelMapper;
    private final ILogService logService;


    @Override
    public Person getUserById(Long id) {
        AttendanceUserDetails userDetails = (AttendanceUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long authenticatedUserId = userDetails.getId();

        Person person = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        LogRequest request = new LogRequest();
        request.setPersonId(authenticatedUserId);
        request.setFirstName(person.getFirstName());
        request.setLastName(person.getLastName());
        request.setAction("GET");
        request.setMessage("User with id " + id + " is fetched");
        request.setServiceName("PersonService");

        logService.logAction(request);
        return personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @Override
    public List<Person> getUserByEmail(String email) {
        return personRepository.findByEmailContaining(email);
    }

    @Override
    public List<Person> getUserByName(String name) {
        return personRepository.findByFirstName(name);
    }

    @Override
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    @Override
    public Person addPerson(AddPersonRequest request) {
        return Optional.of(request)
                .filter(person -> !personRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    Person person = createPerson(request);
                    return personRepository.save(person);
                }).orElseThrow(() -> new AlreadyExistsException("Oops" + request.getEmail() + " already exists"));
    }

    private Person createPerson(AddPersonRequest request) {
        return new Person(
                request.getDateOfBirth(),
                request.getEmail(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName()

        );
    }


    @Override
    public Person updateUser(UpdatePersonRequest person, Long userId) {
        return personRepository.findById(userId)
                .map(existingPerson -> {
                    Person updatedPerson = updateExistingPerson(existingPerson, person);
                    return personRepository.save(updatedPerson);
                }).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    private Person updateExistingPerson(Person existingPerson, UpdatePersonRequest request) {
        existingPerson.setFirstName(request.getFirstName());
        existingPerson.setLastName(request.getLastName());
        existingPerson.setEmail(request.getEmail());
        existingPerson.setPassword(request.getPassword());
        existingPerson.setDateOfBirth(request.getDateOfBirth());
        return existingPerson;
    }

    @Override
    public void deleteUser(Long id) {
        personRepository.findById(id).ifPresentOrElse(personRepository::delete, () -> {
            throw new IllegalArgumentException("User not found");
        });

    }


    @Override
    public PersonDto convertToDto(Person person) {
        PersonDto personDto = modelMapper.map(person, PersonDto.class);
        List<Image> images = imageRepository.findByPersonId(person.getId());
        List<ImageDto> imageDtos = images.stream()
                .map(image -> modelMapper.map(image, ImageDto.class))
                .toList();
        personDto.setImages(imageDtos);
        return personDto;
    }

    @Override
    public List<PersonDto> getConvertedPerson(List<Person> person) {
        return person.stream()
                .map(this::convertToDto).
                toList();
    }
}
