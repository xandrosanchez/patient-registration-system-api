package com.example.patientregistrationsystemapi.repository;

import com.example.patientregistrationsystemapi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
