package com.example.patientregistrationsystemapi.service;

import com.example.patientregistrationsystemapi.dto.DoctorRequest;
import com.example.patientregistrationsystemapi.model.Doctor;
import com.example.patientregistrationsystemapi.model.Ticket;

import java.util.List;

public interface DoctorService {
    public Doctor getDoctorById(Long doctorId);
    public List<Doctor> getAllDoctor();
    public void deleteDoctorById(Long id);
    public void deleteTicketByFullName(String fullName);
    public Doctor saveDoctor(DoctorRequest DoctorRequest);
    public Doctor updateDoctor(Long id, DoctorRequest DoctorRequest);
}
