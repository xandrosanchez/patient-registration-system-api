package com.example.patientregistrationsystemapi.service;

import com.example.patientregistrationsystemapi.dto.ScheduleRequest;
import com.example.patientregistrationsystemapi.dto.TicketRequest;
import com.example.patientregistrationsystemapi.exception.TicketNotFoundException;
import com.example.patientregistrationsystemapi.model.Doctor;
import com.example.patientregistrationsystemapi.model.Ticket;
import com.example.patientregistrationsystemapi.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final DoctorService doctorService;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, DoctorService doctorService) {
        this.ticketRepository = ticketRepository;
        this.doctorService = doctorService;
    }

    /**
     * Получить все билеты.
     *
     * @return Список всех билетов.
     */
    @Override
    public List<Ticket> getAllTicket() {
        return ticketRepository.findAll();
    }

    /**
     * Получить билет по идентификатору.
     *
     * @param id Идентификатор билета.
     * @return Билет с указанным идентификатором.
     * @throws TicketNotFoundException если билет с указанным идентификатором не найден.
     */
    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElseThrow(() ->
                new TicketNotFoundException("Ticket not found with ID " + id));
    }

    /**
     * Сохранить билеты в расписании на основе запроса расписания.
     *
     * @param scheduleRequest Запрос на создание расписания билетов.
     * @return Список созданных билетов.
     */
    @Override
    public List<Ticket> saveTickets(ScheduleRequest scheduleRequest) {
        List<Ticket> schedule = new ArrayList<>();
        Doctor doctor = doctorService.getDoctorById(scheduleRequest.getDoctorId());

        for (int i = 0; i < scheduleRequest.getSlotCount(); i++) {
            Ticket ticket = new Ticket();
            ticket.setDoctor(doctor);
            ticket.setStartTime(scheduleRequest.getStartDate());
            ticket.setEndTime(scheduleRequest.getStartDate().plusMinutes(scheduleRequest.getSlotDuration()));
            schedule.add(ticket);
        }
        return schedule;
    }

    /**
     * Удалить билет по идентификатору.
     *
     * @param id Идентификатор билета для удаления.
     * @throws TicketNotFoundException если билет с указанным идентификатором не найден.
     */
    @Override
    public void deleteTicketById(Long id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
        } else {
            throw new TicketNotFoundException("Ticket not found with ID " + id);
        }
    }

    /**
     * Удалить все билеты, связанные с определенным доктором.
     *
     * @param id Идентификатор доктора, для которого нужно удалить билеты.
     * @throws TicketNotFoundException если билеты для указанного доктора не найдены.
     */
    @Override
    public void deleteTicketByDoctorId(Long id) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            if (ticketRepository.existsByDoctor(doctor)) {
                ticketRepository.deleteByDoctor(doctor);
            } else {
                throw new TicketNotFoundException("Ticket not found for Doctor with ID " + id);
            }
        }
    }

    /**
     * Сохранить новый билет на основе запроса на билет.
     *
     * @param ticketRequest Запрос на создание нового билета.
     * @return Созданный билет.
     */
    @Override
    public Ticket saveTicket(TicketRequest ticketRequest) {
        Doctor doctor = doctorService.getDoctorById(ticketRequest.getDoctorId());
        Ticket ticket = new Ticket(doctor, ticketRequest.getStartTime(), ticketRequest.getEndTime());
        return ticketRepository.save(ticket);
    }

    /**
     * Обновить информацию о билете с заданным идентификатором.
     *
     * @param id            Идентификатор билета для обновления.
     * @param ticketRequest Запрос на обновление билета.
     * @return Обновленный билет.
     * @throws TicketNotFoundException если билет с указанным идентификатором не найден.
     */
    @Override
    public Ticket updateTicket(Long id, TicketRequest ticketRequest) {
        Ticket existingTicket = getTicketById(id);
        Doctor doctor = doctorService.getDoctorById(ticketRequest.getDoctorId());

        existingTicket.setDoctor(doctor);
        existingTicket.setStartTime(ticketRequest.getStartTime());
        existingTicket.setEndTime(ticketRequest.getEndTime());

        return ticketRepository.save(existingTicket);
    }
}
