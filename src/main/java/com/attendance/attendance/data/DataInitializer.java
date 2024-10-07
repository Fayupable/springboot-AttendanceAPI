package com.attendance.attendance.data;

import com.attendance.attendance.entity.*;
import com.attendance.attendance.enums.AttendanceStatus;
import com.attendance.attendance.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
@Transactional
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    private final IPersonRepository personRepository;
    private final IRoleRepository roleRepository;
    private final ICourseAttendanceRepository courseAttendanceRepository;
    private final IScheduleRepository scheduleRepository;
    private final IUniversityRepository universityRepository;
    private final IDepartmentRepository universityDepartmentRepository;
    private final IUniversityCourseRepository universityCourseRepository;
    private final IUniversityCourseDetailsRepository universityCourseDetailsRepository;
    private final IUniversityCourseRequirementsRepository universityCourseRequirementsRepository;
    private final IStudentRepository studentRepository;
    private final IStudentCourseRegistrationRepository studentCourseRegistrationRepository;
    private final ITeacherRepository teacherRepository;
    private final IUserReportsRepository userReportsRepository;
    private final IGeneralReportsRepository generalReportsRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
//        Set<String> defaultRoles = Set.of("ROLE_ADMIN", "ROLE_MODERATOR", "ROLE_STUDENT", "ROLE_TEACHER", "ROLE_MEMBER");
//        createDefaultRoleIfNotExists(defaultRoles);
//        createDefaultUserIfNotExists();
//        createDefaultAdminIfNotExists();
//        createUniversityIfNotExists();
//        createUniversityDepartmentIfNotExists();
//        createUniversityCoursesIfNotExists();
//        createUniversityCourseDetailsIfNotExists();
//        createCourseRequirementsIfNotExists();
//        createDefaultStudentIfNotExists();
//        createDefaultTeacherIfNotExists();
//        assignCoursesToStudents();
//        createGeneralReportsIfNotExists();
//        createDefaultUserReportsIfNotExists();
//        createCoursesAttendanceIfNotExists();

    }

    private void createDefaultUserIfNotExists() {
        Role memberRole = roleRepository.findByRoleName("ROLE_MEMBER").get();
        List<String> userNames = List.of("John Doe", "Jane Smith", "Alice Johnson", "Bob Brown", "Charlie Davis");
        for (int i = 0; i < userNames.size(); i++) {
            String defaultEmail = userNames.get(i).toLowerCase().replace(" ", ".") + "@example.com";
            if (personRepository.existsByEmail(defaultEmail)) {
                continue;
            }
            String[] nameParts = userNames.get(i).split(" ");
            Person person = new Person();
            person.setFirstName(nameParts[0]);
            person.setLastName(nameParts[1]);
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
        List<String> adminNames = List.of("Admin One", "Admin Two");
        for (int i = 0; i < adminNames.size(); i++) {
            String defaultEmail = adminNames.get(i).toLowerCase().replace(" ", ".") + "@example.com";
            if (personRepository.existsByEmail(defaultEmail)) {
                continue;
            }
            String[] nameParts = adminNames.get(i).split(" ");
            Person person = new Person();
            person.setFirstName(nameParts[0]);
            person.setLastName(nameParts[1]);
            person.setEmail(defaultEmail);
            person.setPassword(passwordEncoder.encode("123456"));
            person.setRoles(Set.of(adminRole));
            Date randomDate = generateRandomDate();
            person.setDateOfBirth(randomDate);
            personRepository.save(person);
            System.out.println("Admin created: " + person.getEmail());
        }
    }

    private void createDefaultStudentIfNotExists() {
        Role studentRole = roleRepository.findByRoleName("ROLE_STUDENT").orElseThrow(() -> new RuntimeException("Role not found"));
        List<University> universities = universityRepository.findAll();
        List<UniversityDepartment> departments = universityDepartmentRepository.findAll();

        if (universities.isEmpty() || departments.isEmpty()) {
            System.err.println("No universities or departments found.");
            return;
        }

        List<String> studentNames = List.of("Michael Scott", "Pam Beesly", "Jim Halpert", "Dwight Schrute", "Angela Martin");
        for (int i = 0; i < studentNames.size(); i++) {
            String defaultEmail = studentNames.get(i).toLowerCase().replace(" ", ".") + "@example.com";
            if (personRepository.existsByEmail(defaultEmail)) {
                continue;
            }
            String[] nameParts = studentNames.get(i).split(" ");
            Student student = new Student();
            student.setFirstName(nameParts[0]);
            student.setLastName(nameParts[1]);
            student.setEmail(defaultEmail);
            student.setPassword(passwordEncoder.encode("123456"));
            student.setRoles(Set.of(studentRole));
            student.setDateOfBirth(generateRandomDate());
            student.setStudentNumber("S" + i + 6);
            student.setUniversity(universities.get(i % universities.size()));
            student.setDepartment(departments.get(i % departments.size()));
            studentRepository.save(student);
            System.out.println("Student created: " + student.getEmail());
        }
    }

    private void createDefaultTeacherIfNotExists() {
        Role teacherRole = roleRepository.findByRoleName("ROLE_TEACHER").orElseThrow(() -> new RuntimeException("Role not found"));
        List<University> universities = universityRepository.findAll();
        List<UniversityDepartment> departments = universityDepartmentRepository.findAll();

        if (universities.isEmpty() || departments.isEmpty()) {
            System.err.println("No universities or departments found.");
            return;
        }

        List<String> teacherNames = List.of("Dexter Morgan", "Debra Morgan", "Harry Morgan", "Rita Morgan", "Vince Masuka", "Sergeant Doakes");
        for (int i = 0; i < teacherNames.size(); i++) {
            String defaultEmail = teacherNames.get(i).toLowerCase().replace(" ", ".") + "@example.com";
            if (personRepository.existsByEmail(defaultEmail)) {
                continue;
            }
            String[] nameParts = teacherNames.get(i).split(" ");
            Teacher teacher = new Teacher();
            teacher.setFirstName(nameParts[0]);
            teacher.setLastName(nameParts[1]);
            teacher.setEmail(defaultEmail);
            teacher.setPassword(passwordEncoder.encode("123456"));
            teacher.setRoles(Set.of(teacherRole));
            teacher.setDateOfBirth(generateRandomDate());
            teacher.setUniversity(universities.get(i % universities.size()));
            teacher.setDepartment(departments.get(i % departments.size()));
            teacherRepository.save(teacher);
            System.out.println("Teacher created: " + teacher.getEmail());
        }
    }

    private void createUniversityIfNotExists() {
        List<University> universities = Arrays.asList(
                new University("Harvard University", "Cambridge, MA"),
                new University("Stanford University", "Stanford, CA"),
                new University("Massachusetts Institute of Technology", "Cambridge, MA"),
                new University("University of California, Berkeley", "Berkeley, CA"),
                new University("University of Oxford", "Oxford, UK")
        );

        for (University university : universities) {
            if (!universityRepository.existsByUniversityName(university.getUniversityName())) {
                universityRepository.save(university);
            }
        }
    }

    private void createUniversityDepartmentIfNotExists() {
        List<University> universities = universityRepository.findAll();

        if (universities.isEmpty()) {
            System.out.println("No universities found.");
            return;
        }

        List<String> departmentNames = Arrays.asList(
                "Computer Science", "Mathematics", "Physics", "Chemistry", "Biology"
        );

        for (University university : universities) {
            for (String departmentName : departmentNames) {
                if (!universityDepartmentRepository.existsByDepartmentNameAndUniversity(departmentName, university)) {
                    UniversityDepartment universityDepartment = new UniversityDepartment(departmentName, university);
                    universityDepartmentRepository.save(universityDepartment);
                }
            }
        }
    }

    private void createUniversityCoursesIfNotExists() {
        List<UniversityDepartment> universityDepartments = universityDepartmentRepository.findAll();

        if (universityDepartments.isEmpty()) {
            System.out.println("No departments found.");
            return;
        }

        Map<String, List<UniversityCourse>> departmentCourses = Map.of(
                "Computer Science", List.of(
                        new UniversityCourse("Introduction to Computer Science", "CS101", "Basic concepts in computer science."),
                        new UniversityCourse("Data Structures", "CS102", "Fundamentals of data structures.")
                ),
                "Mathematics", List.of(
                        new UniversityCourse("Calculus I", "MATH101", "Differential and integral calculus."),
                        new UniversityCourse("Linear Algebra", "MATH102", "Introduction to linear algebra.")
                ),
                "Physics", List.of(
                        new UniversityCourse("Physics I", "PHYS101", "Basic physics principles."),
                        new UniversityCourse("Electromagnetism", "PHYS102", "Introduction to electromagnetism.")
                ),
                "Chemistry", List.of(
                        new UniversityCourse("Chemistry I", "CHEM101", "Introduction to general chemistry."),
                        new UniversityCourse("Organic Chemistry", "CHEM102", "Basics of organic chemistry.")
                ),
                "Biology", List.of(
                        new UniversityCourse("Biology I", "BIO101", "Introduction to biology."),
                        new UniversityCourse("Genetics", "BIO102", "Fundamentals of genetics.")
                )
        );

        for (UniversityDepartment department : universityDepartments) {
            List<UniversityCourse> courses = departmentCourses.get(department.getDepartmentName());

            if (courses != null) {
                for (UniversityCourse course : courses) {
                    if (!universityCourseRepository.existsByCourseNameAndDepartment(course.getCourseName(), department)) {
                        course.setDepartment(department);
                        universityCourseRepository.save(course);
                    }
                }
            }
        }
    }

    private void createUniversityCourseDetailsIfNotExists() {
        List<UniversityCourse> universityCourses = universityCourseRepository.findAll();

        if (universityCourses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        for (UniversityCourse course : universityCourses) {
            if (!universityCourseDetailsRepository.existsByCourse(course)) {
                UniversityCourseDetails courseDetails = new UniversityCourseDetails();
                courseDetails.setCourse(course);

                switch (course.getCourseName()) {
                    case "Introduction to Computer Science":
                        courseDetails.setDetailedDescription("This course covers basic concepts in computer science.");
                        break;
                    case "Data Structures":
                        courseDetails.setDetailedDescription("This course introduces fundamental data structures.");
                        break;
                    case "Calculus I":
                        courseDetails.setDetailedDescription("This course covers differential and integral calculus.");
                        break;
                    case "Physics I":
                        courseDetails.setDetailedDescription("This course introduces basic physics principles.");
                        break;
                    case "Organic Chemistry":
                        courseDetails.setDetailedDescription("This course covers the basics of organic chemistry.");
                        break;
                    default:
                        courseDetails.setDetailedDescription("No detailed description available.");
                }

                universityCourseDetailsRepository.save(courseDetails);
            }
        }
    }

    private void createCourseRequirementsIfNotExists() {
        List<UniversityCourse> universityCourses = universityCourseRepository.findAll();

        if (universityCourses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }

        for (UniversityCourse course : universityCourses) {
            if (!universityCourseRequirementsRepository.existsByCourse(course)) {
                CoursesRequirements requirement = new CoursesRequirements();
                requirement.setCourse(course);

                switch (course.getCourseName()) {
                    case "Introduction to Computer Science":
                        requirement.setRequirementDescription("Basic knowledge of algorithms and problem-solving.");
                        requirement.setAttendancePercentage(75);
                        break;
                    case "Data Structures":
                        requirement.setRequirementDescription("Completion of Introduction to Computer Science.");
                        requirement.setAttendancePercentage(80);
                        break;
                    case "Calculus I":
                        requirement.setRequirementDescription("Basic understanding of mathematical functions.");
                        requirement.setAttendancePercentage(85);
                        break;
                    case "Physics I":
                        requirement.setRequirementDescription("Basic physics principles must be understood.");
                        requirement.setAttendancePercentage(70);
                        break;
                    case "Organic Chemistry":
                        requirement.setRequirementDescription("Knowledge of general chemistry is required.");
                        requirement.setAttendancePercentage(90);
                        break;
                    default:
                        requirement.setRequirementDescription("No specific requirements available.");
                        requirement.setAttendancePercentage(60);
                }

                universityCourseRequirementsRepository.save(requirement);
            }
        }
    }

    private void createCoursesAttendanceIfNotExists() {
        List<Student> students = studentRepository.findAll();
        List<UniversityCourse> courses = universityCourseRepository.findAll();

        if (students.isEmpty() || courses.isEmpty()) {
            System.out.println("No students or courses found.");
            return;
        }

        List<CoursesAttendance> attendanceList = new ArrayList<>();
        for (Student student : students) {
            for (int i = 0; i < 3; i++) {
                UniversityCourse course = courses.get((int) (Math.random() * courses.size()));
                AttendanceStatus status = AttendanceStatus.values()[(int) (Math.random() * AttendanceStatus.values().length)];
                CoursesAttendance attendance = new CoursesAttendance();
                attendance.setStudent(student);
                attendance.setCourse(course);
                attendance.setAttendanceDate(LocalDate.now().minusDays((int) (Math.random() * 30)));
                attendance.setAttendanceStatus(status);
                attendanceList.add(attendance);
            }
        }

        for (CoursesAttendance attendance : attendanceList) {
            courseAttendanceRepository.save(attendance);
            System.out.println("Attendance record created for student: " + attendance.getStudent().getStudentNumber() + " - Course: " + attendance.getCourse().getCourseName());
        }
    }

    private void createGeneralReportsIfNotExists() {
        List<GeneralReports> reports = Arrays.asList(
                new GeneralReports("Monthly Attendance Report", LocalDate.now(), "Report content for monthly attendance."),
                new GeneralReports("Yearly Performance Report", LocalDate.now(), "Report content for yearly performance."),
                new GeneralReports("Semester Grades Report", LocalDate.now(), "Report content for semester grades."),
                new GeneralReports("Student Progress Report", LocalDate.now(), "Report content for student progress.")
        );
        for (GeneralReports report : reports) {
            generalReportsRepository.save(report);
            System.out.println("Report created: " + report.getReportName());
        }


    }

    private void createDefaultUserReportsIfNotExists() {
        List<Person> persons = personRepository.findAll();

        if (persons.isEmpty()) {
            System.out.println("No persons found.");
            return;
        }

        List<UserReports> reports = Arrays.asList(
                new UserReports(persons.get(0), "Monthly Report", "Monthly report content.", LocalDate.now()),
                new UserReports(persons.get(1), "Yearly Report", "Yearly report content.", LocalDate.now()),
                new UserReports(persons.get(2), "Semester Report", "Semester report content.", LocalDate.now()),
                new UserReports(persons.get(3), "Progress Report", "Progress report content.", LocalDate.now())
        );


        for (UserReports report : reports) {
            userReportsRepository.save(report);
            System.out.println("User report created: " + report.getTitle() + " for " + report.getPerson().getEmail());
        }
    }

    private void assignCoursesToStudents() {
        List<Student> students = studentRepository.findAll();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            List<UniversityCourse> departmentCourses = universityCourseRepository.findByDepartment(student.getDepartment());

            if (departmentCourses.isEmpty()) {
                System.out.println("No courses found for department: " + student.getDepartment().getDepartmentName());
                continue;
            }

            for (UniversityCourse course : departmentCourses) {
                if (!studentCourseRegistrationRepository.existsByStudentAndCourse(student, course)) {
                    StudentCourseRegistration registration = new StudentCourseRegistration();
                    registration.setStudent(student);
                    registration.setCourse(course);
                    registration.setStatus("PENDING");
                    studentCourseRegistrationRepository.save(registration);
                }
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