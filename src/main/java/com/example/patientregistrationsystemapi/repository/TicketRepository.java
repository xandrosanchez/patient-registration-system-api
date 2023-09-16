package com.example.patientregistrationsystemapi.repository;

import com.example.patientregistrationsystemapi.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
