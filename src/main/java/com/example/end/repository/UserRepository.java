package com.example.end.repository;


import com.example.end.models.User;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

  Optional<User> findByEmail(String email);
  boolean existsByEmail(String email);
  Optional<User> findByIdAndRole(Long id, User.Role role);
    List<User> findAllByRole(User.Role role);
}


