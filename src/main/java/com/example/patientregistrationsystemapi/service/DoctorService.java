package com.example.patientregistrationsystemapi.service;

import com.example.patientregistrationsystemapi.dto.DoctorRequest;
import com.example.patientregistrationsystemapi.model.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor getDoctorById(Long doctorId);
    List<Doctor> getAllDoctor();
    void deleteDoctorById(Long id);
    void deleteTicketByFullName(String fullName);
    Doctor saveDoctor(DoctorRequest DoctorRequest);
    Doctor updateDoctor(Long id, DoctorRequest DoctorRequest);
}
