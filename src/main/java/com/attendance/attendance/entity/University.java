package com.attendance.attendance.entity;

import com.attendance.attendance.enums.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "university_id", updatable = false, nullable = false)
    private Long universityId;

    @Column(name = "location", length = 100, nullable = false)
    private String location;

    @Column(name = "university_name", length = 100, nullable = false)
    private String universityName;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<UniversityDepartment> departments;

    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Student> students;

    @PreRemove
    private void setAllStudentsToMembers() {
        if (students != null) {
            for (Student student : students) {
                student.setDepartment(null);
                student.setRole(Role.MEMBER);
            }
        }
    }

}