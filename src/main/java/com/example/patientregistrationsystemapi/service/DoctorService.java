package com.example.patientregistrationsystemapi.service;

import com.example.patientregistrationsystemapi.model.Doctor;

public interface DoctorService {
    Doctor getDoctorById(Long doctorId);
}
