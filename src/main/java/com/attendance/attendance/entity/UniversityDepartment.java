package com.attendance.attendance.entity;

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
@Table(name = "university_department")
public class UniversityDepartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id", updatable = false, nullable = false)
    private Long departmentId;

    @Column(name = "department_name", length = 100, nullable = false)
    private String departmentName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "university_id", nullable = false, foreignKey = @ForeignKey(name = "fk_university_department_university"))
    private University university;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = false)
    @ToString.Exclude
    private List<UniversityCourse> courses;
}