package com.attendance.attendance.repository;

import com.attendance.attendance.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<Person, Long> {
    boolean existsByEmail(String email);

    Person findByEmail(String email);

}
