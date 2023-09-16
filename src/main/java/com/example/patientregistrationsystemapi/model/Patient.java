package com.example.patientregistrationsystemapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private UUID uuid;

    @NotBlank(message = "Поле fullName не должно быть пустым")
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Date dateOfBirth;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> ticketList;
}
