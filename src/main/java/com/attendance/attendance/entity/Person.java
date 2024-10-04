package com.attendance.attendance.entity;

import com.attendance.attendance.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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

    @Column(length = 100)
    private String password;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20)
    private RoleType role;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserReports> userReports;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> images;


    public Person(Date dateOfBirth, String email, String password, String firstName, String lastName, RoleType role) {
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }



}