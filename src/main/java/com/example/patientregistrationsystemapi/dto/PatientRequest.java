package com.example.patientregistrationsystemapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {
    @NotNull(message = "uuid не может быть null")
    @Positive(message = "uuid должен быть положительным числом")
    private UUID uuid;
    @NotBlank(message = "Поле fullName не должно быть пустым")
    private String fullName;
    @NotNull(message = "dateOfBirth cannot be null")
    private Date dateOfBirth;
}
