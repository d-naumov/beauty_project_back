package com.example.end.entity;


import com.example.end.model.AppointmentStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "appointments")
@Data
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "procedure_id")
  private Procedure procedure;

  @ManyToOne
  @JoinColumn(name = "master_id")
  private Master master;

  @Column(name = "appointment_time")
  private LocalDateTime appointmentTime;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private AppointmentStatus status;

}

