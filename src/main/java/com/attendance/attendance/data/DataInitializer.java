package com.attendance.attendance.data;

import com.attendance.attendance.entity.Person;
import com.attendance.attendance.entity.Role;
import com.attendance.attendance.entity.University;

import com.attendance.attendance.repository.IPersonRepository;
import com.attendance.attendance.repository.IUniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
@Transactional
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    private final IPersonRepository personRepository;
    private final IRoleRepository roleRepository;
    private final IUniversityRepository universityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Set<String> defaultRoles = Set.of("ROLE_ADMIN","ROLE_MODERATOR","ROLE_STUDENT","ROLE_TEACHER","ROLE_MEMBER");
        createDefaultRoleIfNotExists(defaultRoles);
        createDefaultUserIfNotExists();
        createDefaultAdminIfNotExists();
        createUniversityIfNotExists();

    }

    private void createDefaultUserIfNotExists() {
        Role memberRole = roleRepository.findByRoleName("ROLE_MEMBER").get();
        for (int i = 0; i < 5; i++) {
            String defaultEmail = "user" + i + "@email.com";
            if (personRepository.existsByEmail(defaultEmail)) {
                continue;
            }
            Person person = new Person();
            person.setFirstName("The user");
            person.setLastName("User" + i);
            person.setEmail(defaultEmail);
            person.setPassword(passwordEncoder.encode("123456"));
            person.setRoles(Set.of(memberRole));
            Date randomDate = generateRandomDate();
            person.setDateOfBirth(randomDate);
            personRepository.save(person);
            System.out.println("User created: " + person.getEmail());
        }
    }

    private void createDefaultAdminIfNotExists() {

        Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN").get();

        for (int i = 0; i < 2; i++) {
            String defaultEmail = "admin" + i + "@email.com";
            if (personRepository.existsByEmail(defaultEmail)) {
                continue;
            }
            Person person = new Person();
            person.setFirstName("Admin");
            person.setLastName("Admin" + i);
            person.setEmail(defaultEmail);
            person.setPassword(passwordEncoder.encode("123456"));
            person.setRoles(Set.of(adminRole));
            Date randomDate = generateRandomDate();
            person.setDateOfBirth(randomDate);

            personRepository.save(person);
            System.out.println("Admin created: " + person.getEmail());
        }
    }

    private void createUniversityIfNotExists() {
        List<University> universities = Arrays.asList(
                new University("Harvard University", "Cambridge, MA"),
                new University("Stanford University", "Stanford, CA"),
                new University("Massachusetts Institute of Technology", "Cambridge, MA"),
                new University("University of California, Berkeley", "Berkeley, CA"),
                new University("University of Oxford", "Oxford, UK"),
                new University("Ludwig Maximilian University of Munich", "Munich, Germany"),
                new University("Heidelberg University", "Heidelberg, Germany"),
                new University("Humboldt University of Berlin", "Berlin, Germany"),
                new University("Technical University of Munich", "Munich, Germany"),
                new University("University of Freiburg", "Freiburg, Germany"),
                new University("University of Göttingen", "Göttingen, Germany"),
                new University("University of Hamburg", "Hamburg, Germany"),
                new University("University of Stuttgart", "Stuttgart, Germany"),
                new University("RWTH Aachen University", "Aachen, Germany"),
                new University("University of Bonn", "Bonn, Germany")
        );

            for (University university : universities) {
                if (!universityRepository.existsByUniversityName(university.getUniversityName())) {
                    universityRepository.save(university);
                }
            }

    }

    private void createDefaultRoleIfNotExists(Set<String> roles) {
        roles.stream()
                .filter(role -> roleRepository.findByRoleName(role).isEmpty())
                .map(Role::new).forEach(roleRepository::save);

    }

    private Date generateRandomDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1970, Calendar.JANUARY, 1);
        long startMillis = calendar.getTimeInMillis();
        calendar.set(2000, Calendar.DECEMBER, 31);
        long endMillis = calendar.getTimeInMillis();
        long randomMillis = ThreadLocalRandom.current().longs(startMillis, endMillis + 1).findAny().getAsLong();
        return new Date(randomMillis);
    }


    @Override
    public boolean supportsAsyncExecution() {
        return ApplicationListener.super.supportsAsyncExecution();
    }
}
