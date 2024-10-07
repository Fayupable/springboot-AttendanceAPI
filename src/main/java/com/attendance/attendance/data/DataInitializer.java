package com.attendance.attendance.data;

import com.attendance.attendance.entity.*;

import com.attendance.attendance.repository.*;
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
    private final IImageRepository imageRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Set<String> defaultRoles = Set.of("ROLE_ADMIN","ROLE_MODERATOR","ROLE_STUDENT","ROLE_TEACHER","ROLE_MEMBER");
        createDefaultRoleIfNotExists(defaultRoles);
        createDefaultUserIfNotExists();
        createDefaultAdminIfNotExists();
        createUniversityIfNotExists();
        createUniversityDepartmentIfNotExists();
        createUniversityCoursesIfNotExists();
        createUniversityCourseDetailsIfNotExists();
        createCourseRequirementsIfNotExists();
        createDefaultStudentIfNotExists();
        assignCoursesToStudents();


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

    private void createDefaultStudentIfNotExists() {
        Role studentRole = roleRepository.findByRoleName("ROLE_STUDENT").orElseThrow(() -> new RuntimeException("Role not found"));
        List<University> universities = universityRepository.findAll();
        List<UniversityDepartment> departments = universityDepartmentRepository.findAll();

        if (universities.isEmpty() || departments.isEmpty()) {
            System.err.println("No universities or departments found.");
            return;
        }

        for (int i = 0; i < 5; i++) {
            String defaultEmail = "student" + i + "@email.com";
            if (personRepository.existsByEmail(defaultEmail)) {
                continue;
            }

            Student student = new Student();
            student.setFirstName("Student");
            student.setLastName("Student" + i);
            student.setEmail(defaultEmail);
            student.setPassword(passwordEncoder.encode("123456"));
            student.setRoles(Set.of(studentRole));
            student.setDateOfBirth(generateRandomDate());
            student.setStudentNumber("S" + i);
            student.setUniversity(universities.get(i % universities.size()));
            student.setDepartment(departments.get(i % departments.size()));

            studentRepository.save(student);
            System.out.println("Student created: " + student.getEmail());
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

    private void createUniversityDepartmentIfNotExists() {
        List<University> universities = universityRepository.findAll();

        if (universities.isEmpty()) {
            System.out.println("No universities found.");
            return;
        }

        List<String> departmentNames = Arrays.asList(
                "Computer Science", "Mathematics", "Physics", "Chemistry", "Biology",
                "Economics", "Business", "Engineering", "Medicine", "Law",
                "History", "Philosophy", "Psychology", "Sociology", "Political Science"
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
//    private void createUniversityCoursesIfNotExists() {
//        List<UniversityDepartment> universityDepartments = universityDepartmentRepository.findAll();
//
//        if (universityDepartments.isEmpty()) {
//            System.out.println("No departments found.");
//            return;
//        }
//
//        List<UniversityCourse> courses = Arrays.asList(
//                new UniversityCourse("Introduction to Computer Science", "CS101", "Basic concepts in computer science."),
//                new UniversityCourse("Calculus I", "MATH101", "Differential and integral calculus."),
//                new UniversityCourse("Physics I", "PHYS101", "Basic physics principles."),
//                new UniversityCourse("Chemistry I", "CHEM101", "Introduction to general chemistry."),
//                new UniversityCourse("Biology I", "BIO101", "Introduction to biology."),
//                new UniversityCourse("Introduction to Economics", "ECON101", "Principles of economics."),
//                new UniversityCourse("Business Management", "BUS101", "Fundamentals of business management."),
//                new UniversityCourse("Engineering Design", "ENGR101", "Introduction to engineering design principles."),
//                new UniversityCourse("Introduction to Medicine", "MED101", "Basic medical principles."),
//                new UniversityCourse("Introduction to Law", "LAW101", "Basic legal principles."),
//                new UniversityCourse("World History", "HIST101", "Overview of world history."),
//                new UniversityCourse("Philosophy of Science", "PHIL101", "Introduction to philosophy of science."),
//                new UniversityCourse("Psychology I", "PSY101", "Introduction to psychology."),
//                new UniversityCourse("Sociology I", "SOC101", "Introduction to sociology."),
//                new UniversityCourse("Political Science I", "POL101", "Introduction to political science.")
//        );
//
//        for (UniversityDepartment department : universityDepartments) {
//            for (UniversityCourse course : courses) {
//                if (!universityCourseRepository.existsByCourseNameAndDepartment(course.getCourseName(), department)) {
//                    course.setDepartment(department);
//                    universityCourseRepository.save(course);
//                }
//            }
//        }
//    }

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
