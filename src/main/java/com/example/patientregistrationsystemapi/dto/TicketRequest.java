package com.example.patientregistrationsystemapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequest {
    @NotNull(message = "doctorId не может быть null")
    @Positive(message = "doctorId должен быть положительным числом")
    private Long doctorId;
    @NotNull(message = "startTime cannot be null")
    private LocalDateTime startTime;
    @NotNull(message = "End time cannot be null")
    private LocalDateTime endTime;
}
