package com.example.end.service.interfaces;


import com.example.end.entity.User;
import com.example.end.entity.VerificationToken;

public interface VerificationTokenService {
  VerificationToken createVerificationToken(User user, String token);
  VerificationToken getVerificationToken(String token);
  // Другие методы
}
