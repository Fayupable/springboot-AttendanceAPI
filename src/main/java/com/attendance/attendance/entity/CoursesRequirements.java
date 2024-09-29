package com.attendance.attendance.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "courses_requirements")
public class CoursesRequirements {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false, foreignKey = @ForeignKey(name = "fk_requirements_course"))
    private UniversityCourse course;

    @Column(name = "requirement_description", nullable = false)
    private String requirementDescription;

    @Column(name = "attendance_percentage", nullable = false)
    private Integer attendancePercentage;
}
