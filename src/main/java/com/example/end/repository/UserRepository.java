package com.example.end.repository;


import com.example.end.models.User;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  User findByFirstName(String firstname);
  List<User> findByRoles_Name(String roleName);
  Optional<User> findByEmail(String email);
  User findByUsername(String username);
  boolean existsByFirstName(String firstname);
  boolean existsByEmail(String email);

}

