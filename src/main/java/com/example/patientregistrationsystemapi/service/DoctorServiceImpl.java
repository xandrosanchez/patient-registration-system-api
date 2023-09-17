package com.example.patientregistrationsystemapi.service;

import com.example.patientregistrationsystemapi.exception.DoctorNotFoundException;
import com.example.patientregistrationsystemapi.model.Doctor;
import com.example.patientregistrationsystemapi.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DoctorServiceImpl {
    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor getDoctorById(Long id){
        return doctorRepository.findById(id)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with id: " + id));
    }

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor createDoctor(Doctor doctor) {
        doctor.setUuid(UUID.randomUUID());
        return doctorRepository.save(doctor);
    }
}
