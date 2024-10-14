package com.attendance.attendance.service.log;

import com.attendance.attendance.entity.Log;
import com.attendance.attendance.entity.Person;
import com.attendance.attendance.repository.ILogRepository;
import com.attendance.attendance.repository.IPersonRepository;
import com.attendance.attendance.request.log.LogRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class LogService implements ILogService {
    private final Logger logger = Logger.getLogger(LogService.class.getName());
    private final IPersonRepository personRepository;
    private final ILogRepository logRepository;


    public void logAction(LogRequest logRequest) {
        Person person = personRepository.findById(logRequest.getPersonId())
                .orElseThrow(() -> new IllegalArgumentException("Person with id " + logRequest.getPersonId() + " not found"));

        String logMessage = String.format("[%s] Person (ID: %d, Name: %s %s) performed action '%s': %s",
                logRequest.getServiceName(), person.getId(), person.getFirstName(), person.getLastName(),
                logRequest.getAction(), logRequest.getMessage());

        logger.info(logMessage);
        saveLog(logRequest.getServiceName(), person, logRequest.getAction(), logMessage);
    }

    @Override
    public Log getLogById(Long id) {
        return logRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Log with id " + id + " not found"));
    }

    private void saveLog(String serviceName, Person person, String action, String message) {
        Log log = new Log();
        log.setMessage(message);
        log.setTimestamp(LocalDateTime.now());
        log.setServiceName(serviceName);
        log.setPersonId(person);
        log.setAction(action);
        logRepository.save(log);
    }

}
