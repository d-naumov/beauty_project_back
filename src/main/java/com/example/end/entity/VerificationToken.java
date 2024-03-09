package com.example.end.entity;


import lombok.Data;



import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "verification_tokens")
public class VerificationToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String token;

  @OneToOne
  @Column(name = "user_id")
  private User user;

  private Date expiryDate;

  // Другие поля и методы
}

