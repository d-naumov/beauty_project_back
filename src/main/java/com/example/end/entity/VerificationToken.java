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

  public VerificationToken() {
  }

  public VerificationToken(Long id, String token, User user, Date expiryDate) {
    this.id = id;
    this.token = token;
    this.user = user;
    this.expiryDate = expiryDate;
  }
}

