package com.example.end.service;

import com.example.end.dto.CartDto;
import com.example.end.exceptions.CartNotFoundException;
import com.example.end.exceptions.EmptyCartException;
import com.example.end.exceptions.ProcedureNotFoundException;
import com.example.end.exceptions.UserNotFoundException;
import com.example.end.mapping.CartMapper;
import com.example.end.models.Booking;
import com.example.end.models.Cart;
import com.example.end.models.Procedure;
import com.example.end.models.User;
import com.example.end.models.BookingStatus;
import com.example.end.repository.*;
import com.example.end.service.interfaces.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProcedureRepository procedureRepository;
    private final CartMapper cartMapper;
    private final BookingRepository bookingRepository;


    public Optional<Cart> findByUserId(Long userId) {
        return cartRepository.findByUserId(userId);

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository,
                           ProcedureRepository procedureRepository, CartMapper cartMapper, BookingRepository bookingRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.procedureRepository = procedureRepository;
        this.cartMapper = cartMapper;
        this.bookingRepository = bookingRepository;
    }

    public Optional<CartDto> findCartDtoByUserId(Long userId) {
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
        return cartOptional.map(cartMapper::toDto);

    }

    public CartDto addProcedureToCart(Long userId, Long procedureId) throws ProcedureNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        Procedure procedure = procedureRepository.findById(procedureId)
                .orElseThrow(() -> new ProcedureNotFoundException("Procedure not found with id: " + procedureId));

        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart(user));
        if (!cart.getProcedures().contains(procedure)) {
            cart.getProcedures().add(procedure);
        }

        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toDto(savedCart);
    }

    public CartDto removeProcedureFromCart(Long userId, Long procedureId) throws ProcedureNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        Procedure procedure = procedureRepository.findById(procedureId)
                .orElseThrow(() -> new ProcedureNotFoundException("Procedure not found with id: " + procedureId));

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user with id: " + userId));

        cart.getProcedures().remove(procedure);

        Cart savedCart = cartRepository.save(cart);
        return cartMapper.toDto(savedCart);
    }

    public void checkoutCart(Long userId) throws EmptyCartException {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user with id: " + userId));

        if (cart.getProcedures().isEmpty()) {
            throw new EmptyCartException("Cannot checkout an empty cart");
        }

        // Создание нового букинга (заказа) на основе содержимого корзины
        Booking booking = new Booking();
        booking.setUser(cart.getUser()); // Установка пользователя для заказа
        booking.setProcedures(cart.getProcedures()); // Установка процедур для заказа
        booking.setDateTime(LocalDateTime.now()); // Установка текущей даты и времени
        booking.setStatus(BookingStatus.PENDING); // Установка статуса заказа

        // Сохранение букинга (заказа) в базе данных
        bookingRepository.save(booking);

        // Очистка содержимого корзины
        cart.getProcedures().clear();
        cartRepository.save(cart);
    }
}

