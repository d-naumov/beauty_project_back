package com.example.end.repository;

import com.example.end.models.Review;
import com.example.end.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMaster(User master);

}
