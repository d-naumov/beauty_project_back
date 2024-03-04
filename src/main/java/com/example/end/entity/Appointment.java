package com.example.end.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "appointments")
@Getter @Setter
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
  @JoinColumn(name = "master_id")  // Замените "user_id" на "master_id"
  private Master master;  // Замените "user" на "master"

  // Другие поля и методы
}

