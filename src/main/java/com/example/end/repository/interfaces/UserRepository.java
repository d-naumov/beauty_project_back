package com.example.end.repository.interfaces;


import com.example.end.entity.User;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByFirstName(String firstname);
  List<User> findByRoles_Name(String roleName);
  Optional<User> findByEmail(String email);
  boolean existsByFirstName(String firstname);
  boolean existsByEmail(String email);
}

