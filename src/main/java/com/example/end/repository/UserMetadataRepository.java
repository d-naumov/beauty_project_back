package com.example.end.repository;

import com.example.end.models.Review;
import com.example.end.models.UserMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMetadataRepository extends JpaRepository<UserMetadata, Long> {
}
