package com.example.end.entity;


import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "appointments")
@Data
public class Appointment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @Column(name = "user_id")
  private User user;

  @ManyToOne
  @Column(name = "procedure_id")
  private Procedure procedure;

  @ManyToOne
  @Column(name = "master_id")  // Замените "user_id" на "master_id"
  private Master master;  // Замените "user" на "master"

  // Другие поля и методы
}

