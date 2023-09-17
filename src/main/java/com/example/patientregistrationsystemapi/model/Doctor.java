package com.example.patientregistrationsystemapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private UUID uuid;
    @NotBlank(message = "Поле fullName не должно быть пустым")
    @Column(nullable = false)
    private String fullName;;
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Ticket> ticketList;
}
