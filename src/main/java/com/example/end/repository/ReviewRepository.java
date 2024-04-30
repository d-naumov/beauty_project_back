package com.example.end.repository;

import com.example.end.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByUserId(Long userId);

    List<Review> findByUserId(Long masterId);

    @Query("SELECT r FROM Review r WHERE r.user.id = :masterId")
    List<Review> findByMasterId(@Param("masterId") Long masterId);
}