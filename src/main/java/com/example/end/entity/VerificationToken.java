package com.example.end.entity;


import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "verification_tokens")
@Getter @Setter
public class VerificationToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String token;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  private Date expiryDate;

  // Другие поля и методы
}

