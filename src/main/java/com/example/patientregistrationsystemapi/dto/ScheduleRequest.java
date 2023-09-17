package com.example.patientregistrationsystemapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startDate;
    @NotNull(message = "slotDuration не может быть null")
    @Positive(message = "slotDuration должен быть положительным числом")
    private int slotDuration;
    @NotNull(message = "slotCount не может быть null")
    @Positive(message = "slotCount должен быть положительным числом")
    private int slotCount;
    @NotNull(message = "doctorId не может быть null")
    @Positive(message = "doctorId должен быть положительным числом")
    private Long doctorId;
}
