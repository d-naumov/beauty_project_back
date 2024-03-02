package com.example.end.service.interfaces;


import com.example.end.entity.User;

public interface EmailService {
  void sendConfirmationEmail(User user, String confirmationUrl);

}
