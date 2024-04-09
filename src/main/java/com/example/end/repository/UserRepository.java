package com.example.end.repository;


import com.example.end.models.User;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

  User findByFirstName(String firstname);
  Optional<User> findByEmail(String email);
  boolean existsByEmail(String email);


  Optional<Object> findByIdAndRole(Long masterId, User.Role role);

    List<User> findAllByRole(User.Role role);
}


