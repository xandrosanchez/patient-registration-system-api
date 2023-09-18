package com.example.patientregistrationsystemapi.service;

import com.example.patientregistrationsystemapi.dto.ScheduleRequest;
import com.example.patientregistrationsystemapi.dto.TicketRequest;
import com.example.patientregistrationsystemapi.model.Ticket;

import java.time.LocalDate;
import java.util.List;

public interface TicketService {
    List<Ticket> getAllTicket();
    Ticket getTicketById(Long id);
    List<Ticket> saveTickets(ScheduleRequest scheduleRequest);
    void deleteTicketById(Long id);
    void deleteTicketByDoctorId(Long id);
    Ticket saveTicket(TicketRequest ticketRequest);
    Ticket updateTicket(Long id, TicketRequest ticketRequest);
    List<Ticket> getTicketByDoctorAndDate(Long doctorId, LocalDate date);
    List<Ticket> getTicketsByPatientId(Long patientId);
}
