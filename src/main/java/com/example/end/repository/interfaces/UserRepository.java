package com.example.end.repository.interfaces;


import com.example.end.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);
  Optional<User> findByEmail(String email);
}

