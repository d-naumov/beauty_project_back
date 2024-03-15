package com.example.end.repository;


import com.example.end.models.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcedureRepository extends JpaRepository<Procedure, Long> {
    Optional<Procedure> findByName(String name);
}
