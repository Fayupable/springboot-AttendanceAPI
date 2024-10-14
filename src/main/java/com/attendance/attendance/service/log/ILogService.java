package com.attendance.attendance.service.log;

import com.attendance.attendance.entity.Log;
import com.attendance.attendance.entity.Person;
import com.attendance.attendance.request.log.LogRequest;

public interface ILogService {
    void logAction(LogRequest logRequest);

    Log getLogById(Long id);

}
