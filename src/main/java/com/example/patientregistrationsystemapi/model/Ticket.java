package com.example.patientregistrationsystemapi.model;

import jakarta.persistence.*;
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
    @OneToOne
    private Doctor doctor;
    @OneToOne
    private Patient patient;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
