package com.attendance.attendance.service.university;

import com.attendance.attendance.dto.UniversityCourseDetailsDto;
import com.attendance.attendance.entity.UniversityCourseDetails;
import com.attendance.attendance.request.university.course.course.AddUniversityCourseRequest;
import com.attendance.attendance.request.university.course.course.UpdateUniversityCourseRequest;
import com.attendance.attendance.request.university.course.detail.AddUniversityCourseDetailsRequest;
import com.attendance.attendance.request.university.course.detail.UpdateUniversityCourseDetailsRequest;

import java.util.List;

public interface IUniversityCourseDetailsService {
    UniversityCourseDetails getCourseDetailsById(Long id);

    UniversityCourseDetails addCourseDetails(AddUniversityCourseDetailsRequest courseDetails);

    UniversityCourseDetails updateCourseDetails(UpdateUniversityCourseDetailsRequest request, Long id);

    List<UniversityCourseDetails> getAllCourseDetails();

    List<UniversityCourseDetails> getCourseDetailsByCourseId(Long courseId);

    void deleteCourseDetails(Long id);

    UniversityCourseDetailsDto convertToDto(UniversityCourseDetails universityCourseDetails);

    List<UniversityCourseDetailsDto> convertToDtoToList(List<UniversityCourseDetails> universityCourseDetails);




}
