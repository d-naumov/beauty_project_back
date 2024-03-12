package com.example.end.service.interfaces;


import com.example.end.models.dto.UserDto;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  UserDto save(UserDto dto);

  List<UserDto> getAllActiveUser();

  UserDto getActiveUserById(Long id);

  void update(UserDto dto);

  void deleteById(Long id);

  void deleteByName(String name);

  void restoreById(Long id);

  int getActiveUserCount();

  double getTotalCartPriceById(Long userId);

  void addBookingToCart(Long userId, Long bookingId);

  void deleteBookingFromCart(Long userId, Long bookingId);

  void clearCartById(Long userId);
}



