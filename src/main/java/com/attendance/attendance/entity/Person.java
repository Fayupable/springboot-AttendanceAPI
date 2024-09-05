package com.attendance.attendance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    @Column(length = 100)
    private String email;

    @Column(length = 45)
    private String password;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Student> students;

    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Teacher> teachers;

    @OneToMany(mappedBy = "person", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<UserReports> userReports;


//    @PrePersist
//    protected void onCreate() {
//        if (id == null) {
//            id = Instant.now().toEpochMilli() * 1000 + (long) (Math.random() * 1000);
//        }
//    }
}