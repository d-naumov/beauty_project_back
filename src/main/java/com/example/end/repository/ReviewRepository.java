package com.example.end.repository;

import com.example.end.models.Review;
import com.example.end.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByUser(User user);
}
