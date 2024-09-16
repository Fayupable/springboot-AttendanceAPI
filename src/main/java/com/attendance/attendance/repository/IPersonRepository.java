package com.attendance.attendance.repository;

import com.attendance.attendance.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPersonRepository extends JpaRepository<Person, Long> {
    boolean existsByEmail(String email);

    List<Person> findByEmail(String email);

    List<Person> findByFirstName(String name);

}
