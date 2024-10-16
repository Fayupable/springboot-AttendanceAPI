package com.attendance.attendance.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_log")
public class UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_log_roles", joinColumns = @JoinColumn(name = "user_log_id"))
    @Column(name = "role")
    private Set<String> roles;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "method", nullable = false)
    private String method;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "ip_address", nullable = false)
    private String ip;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "device_type")
    private String deviceType;

    @Column(name = "operating_system")
    private String operatingSystem;

    @Column(name = "browser")
    private String browser;

    @Column(name = "response")
    private String response;

    @Column(name = "status")
    private String status;

    @Column(name = "request")
    private String request;

    @Column(name = "exception")
    @Lob
    private String exception;

    @Column(name = "execution_time")
    private Long executionTime;

    @Column(name = "login_time", nullable = false)
    private LocalDateTime loginTime;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "application_version")
    private String applicationVersion;

    @Column(name = "server_name")
    private String serverName;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
