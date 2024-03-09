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

  @OneToMany(mappedBy = "master")
  private Set<Appointment> appointments = new HashSet<>();



}

