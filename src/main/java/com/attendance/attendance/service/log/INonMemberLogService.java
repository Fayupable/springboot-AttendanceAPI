package com.attendance.attendance.service.log;

import com.attendance.attendance.entity.NonMemberLog;
import com.attendance.attendance.request.log.NonMemberLogRequest;

public interface INonMemberLogService {
    void saveLog(NonMemberLog request);

    NonMemberLog addLog(NonMemberLogRequest request);

}
