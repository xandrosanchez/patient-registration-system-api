package com.example.patientregistrationsystemapi.repository;

import com.example.patientregistrationsystemapi.model.Doctor;
import com.example.patientregistrationsystemapi.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByDoctor(Doctor doctor);
    void deleteByDoctor(Doctor doctor);
    boolean existsByDoctor(Doctor doctor);

}
