package com.example.end.service;

import com.example.end.repository.ReviewRepository;
import com.example.end.repository.UserRepository;
import com.example.end.service.interfaces.ReviewService;
import org.springframework.stereotype.Service;



@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ReviewServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean deleteReview(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

