package com.attendance.attendance.service.log;

import com.attendance.attendance.entity.NonMemberLog;
import com.attendance.attendance.repository.INonMemberLogRepository;
import com.attendance.attendance.request.log.NonMemberLogRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NonMemberLogService implements INonMemberLogService {
    private final INonMemberLogRepository nonMemberLogRepository;

    @Override
    public void saveLog(NonMemberLog log) {
        nonMemberLogRepository.save(log);
    }

    @Override
    public NonMemberLog addLog(NonMemberLogRequest request) {
        return Optional.of(request)
                .map(this::createNonMemberLog)
                .map(nonMemberLogRepository::save)
                .orElseThrow(() -> new RuntimeException("Failed to add non-member log"));
    }

    private NonMemberLog createNonMemberLog(NonMemberLogRequest request) {
        NonMemberLog nonMemberLog = new NonMemberLog();
        nonMemberLog.setIp(request.getIp());
        nonMemberLog.setCountry(request.getCountry());
        nonMemberLog.setCity(request.getCity());
        nonMemberLog.setUserAgent(request.getUserAgent());
        nonMemberLog.setDeviceType(request.getDeviceType());
        nonMemberLog.setOperatingSystem(request.getOperatingSystem());
        nonMemberLog.setBrowser(request.getBrowser());
        nonMemberLog.setResponse(request.getResponse());
        nonMemberLog.setStatus(request.getStatus());
        nonMemberLog.setRequest(request.getRequest());
        nonMemberLog.setException(request.getException());
        nonMemberLog.setExecutionTime(request.getExecutionTime());
        nonMemberLog.setPath(request.getPath());
        nonMemberLog.setMethod(request.getMethod());
        nonMemberLog.setAction(request.getAction());
        return nonMemberLog;
    }


}
