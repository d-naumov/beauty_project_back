package com.example.end.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "username")
  @Pattern(regexp = "[A-Z][a-z]{3,}")
  private String username;


  @Column(name = "email")
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
  private String email;
  @Column(name = "is_active")
  private boolean isActive;

  private String password;

  @ManyToMany
  @JoinTable(
          name = "user_roles",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Role> roles = new HashSet<>();
  @OneToOne(mappedBy = "customer")
  private Cart cart;

  // Другие поля и методы

}

