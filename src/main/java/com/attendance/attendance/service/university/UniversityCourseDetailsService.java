package com.attendance.attendance.service.university;

import com.attendance.attendance.dto.UniversityCourseDetailsDto;
import com.attendance.attendance.entity.UniversityCourse;
import com.attendance.attendance.entity.UniversityCourseDetails;
import com.attendance.attendance.repository.IUniversityCourseDetailsRepository;
import com.attendance.attendance.repository.IUniversityCourseRepository;
import com.attendance.attendance.request.university.course.course.AddUniversityCourseRequest;
import com.attendance.attendance.request.university.course.course.UpdateUniversityCourseRequest;
import com.attendance.attendance.request.university.course.detail.AddUniversityCourseDetailsRequest;
import com.attendance.attendance.request.university.course.detail.UpdateUniversityCourseDetailsRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UniversityCourseDetailsService implements IUniversityCourseDetailsService {

    private final IUniversityCourseDetailsRepository universityCourseDetailsRepository;
    private final IUniversityCourseRepository universityCourseRepository;
    private final ModelMapper modelMapper;


    @Override
    public UniversityCourseDetails getCourseDetailsById(Long id) {
        return universityCourseDetailsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Course details not found"));
    }

    @Override
    public UniversityCourseDetails addCourseDetails(AddUniversityCourseDetailsRequest courseRequest) {
        UniversityCourse course = findCourseById(courseRequest.getCourseId());
        UniversityCourseDetails courseDetails = mapToCourseDetails(courseRequest, course);
        return saveCourseDetails(courseDetails);
    }

    @Override
    public UniversityCourseDetails updateCourseDetails(UpdateUniversityCourseDetailsRequest courseDetailsRequest, Long id) {
        UniversityCourseDetails existingCourseDetails = findCourseDetailsById(id);
        UniversityCourse course = findCourseById(courseDetailsRequest.getCourseId());
        updateCourseDetailsFromRequest(existingCourseDetails, courseDetailsRequest, course);
        return saveCourseDetails(existingCourseDetails);
    }

    private UniversityCourseDetails mapToCourseDetails(AddUniversityCourseDetailsRequest courseRequest, UniversityCourse course) {
        UniversityCourseDetails courseDetails = new UniversityCourseDetails();
        courseDetails.setDetailedDescription(courseRequest.getDetailedDescription());
        courseDetails.setCourse(course);
        return courseDetails;
    }

    private UniversityCourseDetails findCourseDetailsById(Long id) {
        return universityCourseDetailsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course details not found"));
    }

    private UniversityCourse findCourseById(Long courseId) {
        return universityCourseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with ID: " + courseId));
    }

    private void updateCourseDetailsFromRequest(UniversityCourseDetails existingCourseDetails, UpdateUniversityCourseDetailsRequest courseDetailsRequest, UniversityCourse course) {
        existingCourseDetails.setDetailedDescription(courseDetailsRequest.getDetailedDescription());
        existingCourseDetails.setCourse(course);
    }

    private UniversityCourseDetails saveCourseDetails(UniversityCourseDetails courseDetails) {
        return universityCourseDetailsRepository.save(courseDetails);
    }

    @Override
    public List<UniversityCourseDetails> getAllCourseDetails() {
        return universityCourseDetailsRepository.findAll();
    }

    @Override
    public List<UniversityCourseDetails> getCourseDetailsByCourseId(Long courseId) {
        return universityCourseDetailsRepository.findByCourse_CourseId(courseId);
    }

    @Override
    public void deleteCourseDetails(Long id) {
        universityCourseDetailsRepository.findById(id).ifPresentOrElse(universityCourseDetailsRepository::delete, () -> {
            throw new IllegalArgumentException("Course details not found");
        });
    }

    public UniversityCourseDetailsDto convertToDto(UniversityCourseDetails courseDetails) {
        UniversityCourseDetailsDto dto = new UniversityCourseDetailsDto();
        dto.setCourseDetailsId(courseDetails.getCourseDetailsId());
        dto.setCourseId(courseDetails.getCourse().getCourseId());
        dto.setDetailedDescription(courseDetails.getDetailedDescription());
        return dto;
    }

    public List<UniversityCourseDetailsDto> convertToDtoToList(List<UniversityCourseDetails> courseDetailsList) {
        return courseDetailsList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
