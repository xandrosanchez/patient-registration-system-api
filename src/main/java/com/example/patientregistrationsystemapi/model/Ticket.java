package com.example.patientregistrationsystemapi.model;

import javax.validation.constraints.NotNull;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "Поле doctor не должно быть пустым")
    @OneToOne
    private Doctor doctor;
    @OneToOne
    private Patient patient;
    @Column(nullable = false)
    @NotNull(message = "End time cannot be null")
    private LocalDateTime startTime;
    @Column(nullable = false)
    @NotNull(message = "End time cannot be null")
    private LocalDateTime endTime;

    public Ticket(Doctor doctor,LocalDateTime startTime, LocalDateTime endTime) {
        this.doctor = doctor;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
