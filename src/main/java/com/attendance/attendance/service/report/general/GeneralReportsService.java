package com.attendance.attendance.service.report.general;

import com.attendance.attendance.dto.GeneralReportsDto;
import com.attendance.attendance.entity.GeneralReports;
import com.attendance.attendance.repository.IGeneralReportsRepository;
import com.attendance.attendance.request.report.general.AddGeneralReportsRequest;
import com.attendance.attendance.request.report.general.UpdateGeneralReportsRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.actuate.web.mappings.MappingsEndpoint;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GeneralReportsService implements IGeneralReportsService {

    private final IGeneralReportsRepository generalReportsRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<GeneralReports> getAllGeneralReports() {
        return generalReportsRepository.findAll();
    }

    @Override
    public GeneralReports getGeneralReportById(Long id) {
        return generalReportsRepository.findById(id).orElseThrow(() -> new RuntimeException("General Report not found"));
    }

    @Override
    public List<GeneralReports> getGeneralReportBetweenDates(LocalDate startDate, LocalDate endDate) {
        return generalReportsRepository.findByReportDateBetween(startDate, endDate);
    }

    @Override
    public List<GeneralReports> getGeneralReportByDate(LocalDate date) {
        return generalReportsRepository.findByReportDate(date);
    }

    @Override
    public GeneralReports addGeneralReport(AddGeneralReportsRequest request) {
        return Optional.of(request)
                .map(req -> {
                    GeneralReports generalReports = new GeneralReports();
                    createGeneralReport(request, generalReports);
                    saveGeneralReport(generalReports);
                    return generalReports;
                }).orElseThrow(() -> new RuntimeException("General Report not found"));
    }

    private void createGeneralReport(AddGeneralReportsRequest request, GeneralReports generalReports) {

        generalReports.setReportName(request.getReportName());
        generalReports.setReportContent(request.getReportContent());
        generalReports.setReportDate(LocalDate.now());
    }

    private void saveGeneralReport(GeneralReports generalReports) {
        generalReportsRepository.save(generalReports);
    }


    @Override
    public GeneralReports updateGeneralReport(UpdateGeneralReportsRequest request, Long id) {
        return generalReportsRepository.findById(id)
                .map(existingGeneralReport -> {
                    GeneralReports updatedGeneralReport = updateExistingGeneralReport(existingGeneralReport, request);
                    generalReportsRepository.save(updatedGeneralReport);
                    return updatedGeneralReport;
                }).orElseThrow(() -> new RuntimeException("General Report not found"));

    }

    private GeneralReports updateExistingGeneralReport(GeneralReports existingGeneralReport, UpdateGeneralReportsRequest request) {
        existingGeneralReport.setReportName(request.getReportName());
        existingGeneralReport.setReportContent(request.getReportContent());
        return existingGeneralReport;
    }

    @Override
    public void deleteGeneralReport(Long id) {
        generalReportsRepository.findById(id)
                .ifPresentOrElse(generalReportsRepository::delete, () -> {
                    throw new RuntimeException("General Report not found");
                });
    }

    @Override
    public GeneralReportsDto convertToDto(GeneralReports generalReports) {
        return modelMapper.map(generalReports, GeneralReportsDto.class);
    }

    @Override
    public List<GeneralReportsDto> convertToDto(List<GeneralReports> generalReports) {
        return generalReports.stream()
                .map(this::convertToDto)
                .toList();
    }
}
