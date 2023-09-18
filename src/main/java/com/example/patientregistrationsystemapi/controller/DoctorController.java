package com.example.patientregistrationsystemapi.controller;

import com.example.patientregistrationsystemapi.dto.DoctorRequest;
import com.example.patientregistrationsystemapi.model.Doctor;
import com.example.patientregistrationsystemapi.service.DoctorService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@Api(tags = "Doctor API", description = "Operations for managing doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get doctor by ID", response = Doctor.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved doctor"),
            @ApiResponse(code = 404, message = "Doctor not found")
    })
    public ResponseEntity<Doctor> getDoctorById(
            @ApiParam(value = "Doctor ID", required = true) @PathVariable Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        return ResponseEntity.ok(doctor);
    }

    @GetMapping
    @ApiOperation(value = "Get all doctors", response = List.class)
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctor();
        return ResponseEntity.ok(doctors);
    }

    @PostMapping
    @ApiOperation(value = "Create a new doctor", response = Doctor.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created doctor"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<Doctor> createDoctor(
            @ApiParam(value = "Doctor data", required = true) @Valid @RequestBody DoctorRequest doctorRequest) {
        Doctor doctor = doctorService.saveDoctor(doctorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctor);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update doctor by ID", response = Doctor.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated doctor"),
            @ApiResponse(code = 404, message = "Doctor not found"),
            @ApiResponse(code = 400, message = "Invalid input")
    })
    public ResponseEntity<Doctor> updateDoctor(
            @ApiParam(value = "Doctor ID", required = true) @PathVariable Long id,
            @ApiParam(value = "Updated doctor data", required = true) @Valid @RequestBody DoctorRequest doctorRequest) {
        Doctor updatedDoctor = doctorService.updateDoctor(id, doctorRequest);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete doctor by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted doctor"),
            @ApiResponse(code = 404, message = "Doctor not found")
    })
    public ResponseEntity<Void> deleteDoctorById(
            @ApiParam(value = "Doctor ID", required = true) @PathVariable Long id) {
        doctorService.deleteDoctorById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteTickets/{fullName}")
    @ApiOperation(value = "Delete tickets by full name of doctor")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully deleted tickets"),
            @ApiResponse(code = 404, message = "Doctor not found")
    })
    public ResponseEntity<Void> deleteTicketsByFullName(
            @ApiParam(value = "Full name of doctor", required = true) @PathVariable String fullName) {
        doctorService.deleteTicketByFullName(fullName);
        return ResponseEntity.noContent().build();
    }
}
