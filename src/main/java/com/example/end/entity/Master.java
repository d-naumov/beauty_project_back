package com.example.end.entity;


import lombok.Data;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "masters")
public class Master {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "firstname")
  private String firstName;

  @Column(name = "lastname")
  private String lastName;

  @Column(name = "specialization" )
  private String specialization;

  @Column(name = "is_active")
  private boolean isActive;

  @OneToMany(mappedBy = "masters")
  private Set<Appointment> appointments = new HashSet<>();

  public Master() {
  }

  public Master(Long id, String firstName, String lastName, String specialization, boolean isActive, Set<Appointment> appointments) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.specialization = specialization;
    this.isActive = isActive;
    this.appointments = appointments;
  }
}

