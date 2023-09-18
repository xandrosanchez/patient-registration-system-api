package com.example.patientregistrationsystemapi.controller;

import com.example.patientregistrationsystemapi.dto.PatientRequest;
import com.example.patientregistrationsystemapi.exception.PatientNotFoundException;
import com.example.patientregistrationsystemapi.model.Patient;
import com.example.patientregistrationsystemapi.service.PatientService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
@Api(tags = "Patient Controller", description = "Управление данными о пациентах")
public class PatientController {

    private final PatientService patientService;
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получить информацию о пациенте по ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешно"),
            @ApiResponse(code = 404, message = "Пациент не найден")
    })
    public ResponseEntity<Patient> getPatientById(
            @ApiParam(value = "Идентификатор пациента", required = true) @PathVariable Long id) {
        try {
            Patient patient = patientService.getPatientById(id);
            return ResponseEntity.ok(patient);
        } catch (PatientNotFoundException e) {
            logger.error("Patient with ID {} not found.", id, e);
            throw e;
        }
    }

    @GetMapping
    @ApiOperation(value = "Получить список всех пациентов")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatient();
        return ResponseEntity.ok(patients);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удалить пациента по ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Успешно"),
            @ApiResponse(code = 404, message = "Пациент не найден")
    })
    public ResponseEntity<Void> deletePatientById(
            @ApiParam(value = "Идентификатор пациента", required = true) @PathVariable Long id) {
        try {
            patientService.deletePatientById(id);
            return ResponseEntity.noContent().build();
        } catch (PatientNotFoundException e) {
            logger.error("Patient with ID {} not found.", id, e);
            throw e;
        }
    }

    @DeleteMapping("/fullName/{fullName}")
    @ApiOperation(value = "Удалить пациентов по полному имени")
    public ResponseEntity<Void> deletePatientsByFullName(
            @ApiParam(value = "Полное имя пациента", required = true) @PathVariable String fullName) {
        patientService.deletePatientByFullName(fullName);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ApiOperation(value = "Создать нового пациента")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Успешно создано"),
            @ApiResponse(code = 400, message = "Ошибка валидации")
    })
    public ResponseEntity<Patient> createPatient(
            @ApiParam(value = "Запрос с данными о пациенте", required = true) @Valid @RequestBody PatientRequest patientRequest) {
        Patient createdPatient = patientService.savePatient(patientRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Обновить информацию о пациенте по ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешно обновлено"),
            @ApiResponse(code = 400, message = "Ошибка валидации"),
            @ApiResponse(code = 404, message = "Пациент не найден")
    })
    public ResponseEntity<Patient> updatePatient(
            @ApiParam(value = "Идентификатор пациента", required = true) @PathVariable Long id,
            @ApiParam(value = "Запрос с обновленными данными о пациенте", required = true) @Valid @RequestBody PatientRequest patientRequest) {
        try {
            Patient updatedPatient = patientService.updatePatient(id, patientRequest);
            return ResponseEntity.ok(updatedPatient);
        } catch (PatientNotFoundException e) {
            logger.error("Patient with ID {} not found.", id, e);
            throw e;
        }
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<String> handlePatientNotFoundException(PatientNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
