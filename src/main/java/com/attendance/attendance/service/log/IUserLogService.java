package com.attendance.attendance.service.log;


import com.attendance.attendance.entity.UserLog;
import com.attendance.attendance.request.log.UserLogRequest;

public interface IUserLogService {
    void saveLog(UserLog log);

    UserLog addLog(UserLogRequest log);

}
