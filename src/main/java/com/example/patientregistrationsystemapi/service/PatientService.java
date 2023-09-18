package com.example.patientregistrationsystemapi.service;

import com.example.patientregistrationsystemapi.dto.PatientRequest;
import com.example.patientregistrationsystemapi.model.Patient;

import java.util.List;

public interface PatientService {
    Patient getPatientById(Long PatientId);
    List<Patient> getAllPatient();
    void deletePatientById(Long id);
    void deletePatientByFullName(String fullName);
    Patient savePatient(PatientRequest patientRequest);
    Patient updatePatient(Long id, PatientRequest patientRequest);
}
