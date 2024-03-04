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
  private Long id;

  @Column(nullable = false)
  private String firstName;

  @Column(nullable = false)
  private String lastName;

  @Column(nullable = false)
  private String speciality;

  @OneToMany(mappedBy = "master")
  private Set<Appointment> appointments = new HashSet<>();
}

