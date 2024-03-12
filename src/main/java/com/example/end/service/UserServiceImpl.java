package com.example.end.service;


import com.example.end.models.Cart;
import com.example.end.models.dto.UserDto;
import com.example.end.mapping.UserMappingService;
import com.example.end.models.User;
import com.example.end.repository.UserRepository;
import com.example.end.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private UserMappingService mappingService;


    @Override
    public UserDto save(UserDto dto) {
        User entity = mappingService.mapDtoToEntity(dto);
        entity = repository.save(entity);
        return mappingService.mapEntityToDto(entity);
    }

    @Override
    public List<UserDto> getAllActiveUser() {
        return repository.findAll()
                .stream()
                .map(u -> mappingService.mapEntityToDto(u))
                .toList();
    }

    public UserDto getActiveUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
        return mappingService.mapEntityToDto(user);
    }



    public UserDto getAllUserById(Long id) {
        User entity = repository.findById(id).orElse(null);
        return entity == null ? null : mappingService.mapEntityToDto(entity);
    }

    @Override
    public void update(UserDto dto) {
        User user = repository.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + dto.getId()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());

        repository.save(user);
    }
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        User user = repository.findByFirstName(name);
        if (user != null) {
            repository.delete(user);
        } else {
            throw new NoSuchElementException("User not found with name: " + name);
        }
    }

    @Override
    @Transactional
    public void restoreById(Long id) {
        User user = repository.findById(id).orElse(null);

        if (user != null) {
            user.setActive(true);
            repository.save(user);
        }
    }


    @Override
    public int getActiveUserCount() {
        return repository.countByActiveTrue();
    }

    @Override
    public double getTotalCartPriceById(Long userId) {

        return 0;
    }
//        User user = repository.findById(userId)
//                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + userId));
//        Cart cart = user.getCart();
//
//        if (cart == null || cart.getBooking().isEmpty()) {
//            return 0.0;
//        }
//
//        double totalCartPrice = cart.getBooking().stream()
//                .mapToDouble(CartBooking::getPrice)
//                .sum();
//
//        return totalCartPrice;
//    }


    @Override
    public void addBookingToCart(Long userId, Long bookingId) {

    }

    @Override
    public void deleteBookingFromCart(Long userId, Long bookingId) {

    }

    @Override
    public void clearCartById(Long userId) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}




