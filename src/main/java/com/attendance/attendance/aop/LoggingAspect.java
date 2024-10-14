package com.attendance.attendance.aop;

import com.attendance.attendance.entity.Person;
import com.attendance.attendance.repository.IPersonRepository;
import com.attendance.attendance.request.log.LogRequest;
import com.attendance.attendance.security.user.AttendanceUserDetails;
import com.attendance.attendance.service.log.ILogService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private final ILogService logService;
    private final IPersonRepository personRepository;

    @AfterReturning(
            pointcut = "execution(* com.attendance.attendance.service..*.*(..)) && !within(com.attendance.attendance.service.log.LogService))",
            returning = "result"
    )
    public void logServiceMethod(JoinPoint joinPoint, Object result) {
        logMethodCall(joinPoint, "SUCCESS");
    }

    @AfterThrowing(
            pointcut = "execution(* com.attendance.attendance.service..*.*(..)) && !within(com.attendance.attendance.service.log.LogService))",
            throwing = "error"
    )
    public void logAccessDenied(JoinPoint joinPoint, Throwable error) {
        if (error instanceof org.springframework.security.access.AccessDeniedException) {
            logMethodCall(joinPoint, "ACCESS_DENIED");
        }
    }


    private void logMethodCall(JoinPoint joinPoint, String status) {
        try {
            String methodName = joinPoint.getSignature().getName();
            String className = joinPoint.getTarget().getClass().getSimpleName();

            var authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || !authentication.isAuthenticated()) {
                System.out.println("User not authenticated");
                return;
            }

            AttendanceUserDetails userDetails = (AttendanceUserDetails) authentication.getPrincipal();
            Long authenticatedUserId = userDetails != null ? userDetails.getId() : null;

            if (authenticatedUserId == null) {
                System.out.println("User id null");
                return;
            }

            Person person = personRepository.findById(authenticatedUserId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            LogRequest logRequest = new LogRequest();
            logRequest.setPersonId(authenticatedUserId);
            logRequest.setAction(methodName.toUpperCase());
            logRequest.setMessage("Method " + methodName + " in " + className + " was called, status: " + status);
            logRequest.setServiceName(className);

            logService.logAction(logRequest);
        } catch (Exception e) {
            System.err.println("Log error: " + e.getMessage());
        }
    }
}