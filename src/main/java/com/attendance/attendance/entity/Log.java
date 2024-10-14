package com.attendance.attendance.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "log_message", length = 1000)
    private String message;
    @Column(name = "log_timestamp")
    private LocalDateTime timestamp;
    @Column(name = "service_name", length = 100)
    private String serviceName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false, foreignKey = @ForeignKey(name = "fk_log_person_id"))
    private Person personId;
    
    @Column(name = "action", length = 100)
    private String action;


}
