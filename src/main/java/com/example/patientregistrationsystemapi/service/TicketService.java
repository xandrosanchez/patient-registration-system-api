package com.example.patientregistrationsystemapi.service;

import com.example.patientregistrationsystemapi.dto.ScheduleRequest;
import com.example.patientregistrationsystemapi.dto.TicketRequest;
import com.example.patientregistrationsystemapi.model.Ticket;
import jakarta.jws.WebParam;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketService {
    public List<Ticket> getAllTicket();
    public Ticket getTicketById(Long id);
    public List<Ticket> saveTickets(ScheduleRequest scheduleRequest);
    public void deleteTicketById(Long id);
    public void deleteTicketByDoctorId(Long id);
    public Ticket saveTicket(TicketRequest ticketRequest);
    Ticket updateTicket(Long id, TicketRequest ticketRequest);

}
