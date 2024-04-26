package com.example.end.repository;

import com.example.end.models.Review;
import com.example.end.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByUserId(Long userId);

    List<Review> findByUserId(Long masterId);
    @Query("SELECT r FROM Review r WHERE r.master.id = :masterId")
    List<Review> findByMasterId(Long masterId);
}
