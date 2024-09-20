package com.attendance.attendance.service.report.user;

import com.attendance.attendance.dto.UserReportsDto;
import com.attendance.attendance.entity.Person;
import com.attendance.attendance.entity.UserReports;
import com.attendance.attendance.exceptions.NotFoundException;
import com.attendance.attendance.repository.IPersonRepository;
import com.attendance.attendance.repository.IUserReportsRepository;
import com.attendance.attendance.request.report.user.AddUserReportsRequest;
import com.attendance.attendance.request.report.user.UpdateUserReportsRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserReportsService implements IUserReportsService {
    private final IUserReportsRepository userReportsRepository;
    private final IPersonRepository personRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<UserReports> getAllUserReports() {
        return userReportsRepository.findAll();
    }

    @Override
    public UserReports getUserReportById(Long id) {
        return userReportsRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User report not found")
        );
    }

    @Override
    public UserReports addUserReport(AddUserReportsRequest request) {
        return Optional.of(request)
                .map(req -> {
                    UserReports userReports = new UserReports();
                    createUserReport(req, userReports);
                    saveUserReport(userReports);
                    return userReports;
                }).orElseThrow(() -> new NotFoundException("User report not found"));
    }

    private void createUserReport(AddUserReportsRequest request, UserReports userReports) {
        userReports.setTitle(request.getTitle());
        userReports.setContent(request.getContent());
        userReports.setDate(LocalDateTime.now());
        checkPersonId(request.getPersonId(), userReports);
    }

    private void checkPersonId(Long personId, UserReports userReports) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new NotFoundException("Person not found"));
        userReports.setPerson(person);
    }

    private void saveUserReport(UserReports userReports) {
        userReportsRepository.save(userReports);
    }

    @Override
    public UserReports updateUserReport(UpdateUserReportsRequest request, Long id) {
        return userReportsRepository.findById(id)
                .map(userReports -> {
                    updateUserReport(request, userReports);
                    return userReports;
                }).orElseThrow(() -> new NotFoundException("User report not found"));
    }

    private void updateUserReport(UpdateUserReportsRequest request, UserReports userReports) {
        userReports.setTitle(request.getTitle());
        userReports.setContent(request.getContent());
        userReports.setDate(LocalDateTime.now());
    }

    @Override
    public void deleteUserReport(Long id) {
        userReportsRepository.findById(id).ifPresentOrElse(
                userReportsRepository::delete,
                () -> {
                    throw new NotFoundException("User report not found");
                }
        );

    }

    @Override
    public List<UserReports> getUserReportsByTitle(String reportName) {
        return userReportsRepository.findUserReportsByTitle(reportName);
    }

    @Override
    public List<UserReports> getUserReportsByDate(LocalDateTime date) {
        return userReportsRepository.findUserReportsByDate(date);
    }

    @Override
    public List<UserReports> getUserReportsByTitleAndDate(String reportName, LocalDateTime date) {
        return userReportsRepository.findUserReportsByTitleAndDate(reportName, date);
    }

    @Override
    public List<UserReports> getUserReportsByTitleAndContentContaining(String reportName, String content) {
        return userReportsRepository.findUserReportsByTitleAndContentContaining(reportName, content);
    }

    @Override
    public List<UserReports> getUserReportsByDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return userReportsRepository.findUserReportsByDateBetween(startDate, endDate);
    }

    @Override
    public List<UserReports> getUserReportsByTitleAndDateBetween(String reportName, LocalDateTime startDate, LocalDateTime endDate) {
        return userReportsRepository.findUserReportsByTitleAndDateBetween(reportName, startDate, endDate);
    }

    @Override
    public List<UserReports> getUserReportsByTitleAndContentContainingAndDateBetween(String reportName, String content, LocalDateTime startDate, LocalDateTime endDate) {
        return userReportsRepository.findUserReportsByTitleAndContentContainingAndDateBetween(reportName, content, startDate, endDate);
    }

    @Override
    public List<UserReports> getUserReportsByContentContaining(String content) {
        return userReportsRepository.findUserReportsByContentContaining(content);
    }

    @Override
    public List<UserReports> getUserReportsByPersonId(Long personId) {
        return userReportsRepository.findUserReportsByPersonId(personId);
    }

    @Override
    public UserReportsDto convertToDto(UserReports userReports) {
        return modelMapper.map(userReports, UserReportsDto.class);
    }

    @Override
    public List<UserReportsDto> convertToDto(List<UserReports> userReports) {
        return userReports.stream()
                .map(this::convertToDto)
                .toList();
    }
}
