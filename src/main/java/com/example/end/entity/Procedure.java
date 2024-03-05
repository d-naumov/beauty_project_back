package com.example.end.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "procedures")
@Getter @Setter
public class Procedure {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private double price;


}

