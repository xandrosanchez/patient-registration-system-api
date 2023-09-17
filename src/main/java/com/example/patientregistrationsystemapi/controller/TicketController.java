package com.example.patientregistrationsystemapi.controller;

import com.example.patientregistrationsystemapi.dto.ScheduleRequest;
import com.example.patientregistrationsystemapi.dto.TicketRequest;
import com.example.patientregistrationsystemapi.exception.TicketNotFoundException;
import com.example.patientregistrationsystemapi.model.Ticket;
import com.example.patientregistrationsystemapi.service.TicketService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@Validated
@Api(tags = "Ticket Management", description = "APIs for managing tickets")
public class TicketController {
    private final TicketService ticketService;
    private final Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @ApiOperation(value = "Get all tickets", notes = "Retrieve a list of all tickets.")
    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        try {
            List<Ticket> tickets = ticketService.getAllTicket();
            logger.info("Retrieved all tickets");
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while retrieving all tickets: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get a ticket by ID", notes = "Retrieve a ticket by its ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ticket found successfully"),
            @ApiResponse(code = 404, message = "Ticket not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(
            @ApiParam(value = "ID of the ticket to retrieve", required = true)
            @PathVariable Long id) {
        try {
            Ticket ticket = ticketService.getTicketById(id);
            logger.info("Retrieved ticket by ID: " + id);
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } catch (TicketNotFoundException e) {
            logger.error("Ticket not found with ID " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error while retrieving ticket by ID " + id + ": " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Create tickets", notes = "Create multiple tickets based on schedule request.")
    @PostMapping("/schedule")
    public ResponseEntity<List<Ticket>> createTickets(
            @ApiParam(value = "Schedule request to create tickets", required = true)
            @Valid @RequestBody ScheduleRequest scheduleRequest) {
        try {
            List<Ticket> tickets = ticketService.saveTickets(scheduleRequest);
            logger.info("Created tickets based on schedule request");
            return new ResponseEntity<>(tickets, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error while creating tickets based on schedule request: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete a ticket by ID", notes = "Delete a ticket by its ID.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Ticket deleted successfully"),
            @ApiResponse(code = 404, message = "Ticket not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicketById(
            @ApiParam(value = "ID of the ticket to delete", required = true)
            @PathVariable Long id) {
        try {
            ticketService.deleteTicketById(id);
            logger.info("Deleted ticket by ID: " + id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (TicketNotFoundException e) {
            logger.error("Ticket not found with ID " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error while deleting ticket by ID " + id + ": " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Delete tickets by doctor ID", notes = "Delete all tickets associated with a doctor by doctor's ID.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Tickets deleted successfully"),
            @ApiResponse(code = 404, message = "Doctor not found")
    })
    @DeleteMapping("/doctor/{id}")
    public ResponseEntity<Void> deleteTicketsByDoctorId(
            @ApiParam(value = "ID of the doctor to delete tickets for", required = true)
            @PathVariable Long id) {
        try {
            ticketService.deleteTicketByDoctorId(id);
            logger.info("Deleted tickets by doctor ID: " + id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (TicketNotFoundException e) {
            logger.error("Doctor not found with ID " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error while deleting tickets by doctor ID " + id + ": " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Create a ticket", notes = "Create a single ticket.")
    @PostMapping
    public ResponseEntity<Ticket> createTicket(
            @ApiParam(value = "Ticket request to create a ticket", required = true)
            @Valid @RequestBody TicketRequest ticketRequest) {
        try {
            Ticket ticket = ticketService.saveTicket(ticketRequest);
            logger.info("Created a ticket");
            return new ResponseEntity<>(ticket, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error while creating a ticket: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Update a ticket", notes = "Update an existing ticket by its ID.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Ticket updated successfully"),
            @ApiResponse(code = 404, message = "Ticket not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(
            @ApiParam(value = "ID of the ticket to update", required = true)
            @PathVariable Long id,
            @ApiParam(value = "Ticket request to update the ticket", required = true)
            @Valid @RequestBody TicketRequest ticketRequest) {
        try {
            Ticket ticket = ticketService.updateTicket(id, ticketRequest);
            logger.info("Updated ticket by ID: " + id);
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } catch (TicketNotFoundException e) {
            logger.error("Ticket not found with ID " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            logger.error("Error while updating ticket by ID " + id + ": " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
