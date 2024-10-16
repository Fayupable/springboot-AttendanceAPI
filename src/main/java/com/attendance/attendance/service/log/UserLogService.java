package com.attendance.attendance.service.log;


import com.attendance.attendance.entity.Person;
import com.attendance.attendance.entity.UserLog;
import com.attendance.attendance.repository.IPersonRepository;
import com.attendance.attendance.repository.IUserLogRepository;
import com.attendance.attendance.request.log.UserLogRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserLogService implements IUserLogService {
    private final IUserLogRepository userLogRepository;
    private final IPersonRepository personRepository;

    @Override
    public void saveLog(UserLog log) {
        userLogRepository.save(log);
    }

    @Override
    public UserLog addLog(UserLogRequest log) {
        return Optional.of(log)
                .map(this::createUserLog)
                .map(userLogRepository::save)
                .orElseThrow(() -> new RuntimeException("Failed to add user log"));
    }

    private UserLog createUserLog(UserLogRequest request) {
        UserLog userLog = new UserLog();
        Person person = personRepository.findById(request.getPersonId())
                .orElseThrow(() -> new RuntimeException("Person not found"));
        userLog.setPerson(person);
        userLog.setRoles(request.getRoles());
        userLog.setAction(request.getAction());
        userLog.setMethod(request.getMethod());
        userLog.setPath(request.getPath());
        userLog.setIp(request.getIp());
        userLog.setCountry(request.getCountry());
        userLog.setCity(request.getCity());
        userLog.setUserAgent(request.getUserAgent());
        userLog.setDeviceType(request.getDeviceType());
        userLog.setOperatingSystem(request.getOperatingSystem());
        userLog.setBrowser(request.getBrowser());
        userLog.setResponse(request.getResponse());
        userLog.setStatus(request.getStatus());
        userLog.setRequest(request.getRequest());
        userLog.setException(request.getException());
        userLog.setExecutionTime(request.getExecutionTime());
        return userLog;
    }

}
