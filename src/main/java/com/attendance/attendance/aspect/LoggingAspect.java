package com.attendance.attendance.aspect;

import com.attendance.attendance.entity.NonMemberLog;
import com.attendance.attendance.entity.Person;
import com.attendance.attendance.entity.UserLog;
import com.attendance.attendance.security.user.AttendanceUserDetails;
import com.attendance.attendance.service.log.INonMemberLogService;
import com.attendance.attendance.service.log.IUserLogService;
import com.attendance.attendance.service.person.IPersonService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private final IUserLogService userLogService;
    private final INonMemberLogService nonMemberLogService;
    private final IPersonService personService;
    private final HttpServletRequest request;
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


    @Around("execution(* com.attendance.attendance.controller.*.*(..))")
    public Object logExecutionDetails(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            logException(joinPoint, e);
            throw e;
        }
        long executionTime = System.currentTimeMillis() - startTime;
        String methodName = joinPoint.getSignature().getName();
        String path = request.getRequestURI();
        String httpMethod = request.getMethod();
        String ip = getClientIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        String status = result != null ? "Success" : "Failed";
        String requestBody = getRequestPayload(joinPoint.getArgs());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        logger.debug("Authentication: {}", (authentication != null ? authentication.getName() : "null"));
        if (authentication != null) {
            logger.debug("Authorities: {}", authentication.getAuthorities());
        }

        Long personId = extractPersonId(authentication);
        logger.debug("Extracted personId: {}", personId);

        if (authentication != null && authentication.isAuthenticated() && !isAnonymous(authentication)) {
            Set<String> roles = authentication.getAuthorities().stream()
                    .map(auth -> auth.getAuthority())
                    .collect(Collectors.toSet());
            if (personId != null) {
                Person person = personService.getUserById(personId);
                if (person != null) {
                    logUserLog(person, roles, methodName, path, httpMethod, ip, userAgent, executionTime, requestBody, status);
                } else {
                    logNonMemberLog("Member Action - Person Not Found", methodName, path, httpMethod, ip, userAgent, executionTime, requestBody, status);
                }
            } else {
                logNonMemberLog("Member Action - No Person ID", methodName, path, httpMethod, ip, userAgent, executionTime, requestBody, status);
            }
        } else {
            logNonMemberLog("Non-Member Action", methodName, path, httpMethod, ip, userAgent, executionTime, requestBody, status);
        }

        return result;
    }

    private void logUserLog(Person person, Set<String> roles, String methodName, String path, String method, String ip,
                            String userAgent, long executionTime, String requestBody, String status) {
        UserLog userLog = new UserLog();
        userLog.setPerson(person);
        userLog.setRoles(roles);
        userLog.setAction(methodName);
        userLog.setMethod(method);
        userLog.setPath(path);
        userLog.setIp(ip);
        userLog.setUserAgent(userAgent);
        userLog.setResponse("Response data");
        userLog.setStatus(status);
        userLog.setRequest(requestBody);
        userLog.setException(null);
        userLog.setExecutionTime(executionTime);
        userLog.setLoginTime(LocalDateTime.now());
        userLog.setSessionId(request.getSession().getId());
        userLog.setApplicationVersion("1.0.0");
        userLog.setServerName(request.getServerName());

        userLogService.saveLog(userLog);
    }

    private void logNonMemberLog(String action, String methodName, String path, String method, String ip,
                                 String userAgent, long executionTime, String requestBody, String status) {
        NonMemberLog nonMemberLog = new NonMemberLog();
        nonMemberLog.setAction(action);
        nonMemberLog.setMethod(method);
        nonMemberLog.setPath(path);
        nonMemberLog.setIp(ip);
        nonMemberLog.setUserAgent(userAgent);
        nonMemberLog.setResponse("Response data");
        nonMemberLog.setStatus(status);
        nonMemberLog.setRequest(requestBody);
        nonMemberLog.setException(null);
        nonMemberLog.setExecutionTime(executionTime);
        nonMemberLog.setLoginTime(LocalDateTime.now());
        nonMemberLog.setSessionId(request.getSession().getId());
        nonMemberLog.setApplicationVersion("1.0.0");
        nonMemberLog.setServerName(request.getServerName());

        nonMemberLogService.saveLog(nonMemberLog);
    }

    private void logException(ProceedingJoinPoint joinPoint, Exception e) {
        String methodName = joinPoint.getSignature().getName();
        String path = request.getRequestURI();
        String method = request.getMethod();
        String ip = getClientIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        String requestBody = getRequestPayload(joinPoint.getArgs());
        String status = "Failed";
        String exceptionMessage = e.getMessage();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        logger.debug("Exception in method: {}", methodName);
        logger.debug("Exception message: {}", exceptionMessage);

        if (authentication != null && authentication.isAuthenticated() && !isAnonymous(authentication)) {
            Set<String> roles = authentication.getAuthorities().stream()
                    .map(auth -> auth.getAuthority())
                    .collect(Collectors.toSet());
            Long personId = extractPersonId(authentication);
            Person person = (personId != null) ? personService.getUserById(personId) : null;

            UserLog userLog = new UserLog();
            userLog.setPerson(person);
            userLog.setRoles(roles);
            userLog.setAction(methodName);
            userLog.setMethod(method);
            userLog.setPath(path);
            userLog.setIp(ip);
            userLog.setUserAgent(userAgent);
            userLog.setResponse(null);
            userLog.setStatus(status);
            userLog.setRequest(requestBody);
            userLog.setException(exceptionMessage);
            userLog.setExecutionTime(0L);
            userLog.setLoginTime(LocalDateTime.now());
            userLog.setSessionId(request.getSession().getId());
            userLog.setApplicationVersion("1.0.0");
            userLog.setServerName(request.getServerName());

            userLogService.saveLog(userLog);
        } else {
            NonMemberLog nonMemberLog = new NonMemberLog();
            nonMemberLog.setAction("Non-member Action with Exception");
            nonMemberLog.setMethod(method);
            nonMemberLog.setPath(path);
            nonMemberLog.setIp(ip);
            nonMemberLog.setUserAgent(userAgent);
            nonMemberLog.setResponse(null);
            nonMemberLog.setStatus(status);
            nonMemberLog.setRequest(requestBody);
            nonMemberLog.setException(exceptionMessage);
            nonMemberLog.setExecutionTime(0L);
            nonMemberLog.setLoginTime(LocalDateTime.now());
            nonMemberLog.setSessionId(request.getSession().getId());
            nonMemberLog.setApplicationVersion("1.0.0");
            nonMemberLog.setServerName(request.getServerName());

            nonMemberLogService.saveLog(nonMemberLog);
        }
    }

    //
    //take wrong api address fix this
    //
    private String getClientIpAddress(HttpServletRequest request) {
        String[] headersToCheck = {
                "X-Forwarded-For",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_X_FORWARDED_FOR",
                "HTTP_X_FORWARDED",
                "HTTP_X_CLUSTER_CLIENT_IP",
                "HTTP_CLIENT_IP",
                "HTTP_FORWARDED_FOR",
                "HTTP_FORWARDED",
                "HTTP_VIA",
                "REMOTE_ADDR"
        };
        for (String header : headersToCheck) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip.contains(",") ? ip.split(",")[0].trim() : ip.trim();
            }
        }
        return request.getRemoteAddr();
    }

    private boolean isAnonymous(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ANONYMOUS"));
    }

    private Long extractPersonId(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated() && !isAnonymous(authentication)) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof AttendanceUserDetails) {
                return ((AttendanceUserDetails) principal).getId();
            }
        }
        return null;
    }

    private String getRequestPayload(Object[] args) {
        if (args == null || args.length == 0) {
            return "No Payload";
        }

        //
        //Show info about password and gmail fix this!!!!!!!!
        //
        String payload = Arrays.toString(args);

        // Filter out sensitive fields
        payload = payload.replaceAll("(?i)\"password\":\"[^\"]*\"", "\"password\":\"[FILTERED]\"");
        payload = payload.replaceAll("(?i)\"email\":\"[^\"]*\"", "\"email\":\"[FILTERED]\"");
        payload = payload.replaceAll("(?i)password=[^,\\]]*", "password=[FILTERED]");
        payload = payload.replaceAll("(?i)email=[^,\\]]*", "email=[FILTERED]");

        return payload;
    }


}