package com.example.patientregistrationsystemapi.repository;

import com.example.patientregistrationsystemapi.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
