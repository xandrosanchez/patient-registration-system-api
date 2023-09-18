package com.example.patientregistrationsystemapi.repository;

import com.example.patientregistrationsystemapi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    void deleteByFullName(String fullName);
}
