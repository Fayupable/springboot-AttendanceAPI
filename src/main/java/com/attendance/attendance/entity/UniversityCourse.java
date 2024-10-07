package com.attendance.attendance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "university_course")
public class UniversityCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id", updatable = false, nullable = false)
    private Long courseId;

    @Column(name = "course_name", length = 100, nullable = false)
    private String courseName;

    @Column(name = "course_code", length = 20, nullable = false)
    private String courseCode;

    @Column(name = "description", length = 255)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false, foreignKey = @ForeignKey(name = "fk_university_course_department"))
    private UniversityDepartment department;

    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UniversityCourseDetails> courseDetails;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CoursesRequirements> courseRequirements;

    public UniversityCourse(String courseName, String courseCode, String description) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.description = description;
    }
}