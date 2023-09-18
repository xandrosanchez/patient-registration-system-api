package com.example.patientregistrationsystemapi.repository;

import com.example.patientregistrationsystemapi.model.Doctor;
import com.example.patientregistrationsystemapi.model.Patient;
import com.example.patientregistrationsystemapi.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByDoctorAndStartTimeBetween(Doctor doctor, LocalDateTime startTime, LocalDateTime endTime);
    void deleteByDoctor(Doctor doctor);
    boolean existsByDoctor(Doctor doctor);
    List<Ticket> findByPatient(Patient patient);
}
