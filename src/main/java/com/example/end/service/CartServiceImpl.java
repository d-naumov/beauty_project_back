package com.example.end.service;

import com.example.end.exceptions.CartNotFoundException;
import com.example.end.exceptions.EmptyCartException;
import com.example.end.exceptions.ProcedureNotFoundException;
import com.example.end.exceptions.UserNotFoundException;
import com.example.end.models.Cart;
import com.example.end.models.Procedure;
import com.example.end.models.User;
import com.example.end.repository.CartRepository;
import com.example.end.repository.CategoryRepository;
import com.example.end.repository.ProcedureRepository;
import com.example.end.repository.UserRepository;
import com.example.end.service.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProcedureRepository procedureRepository;
   private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ProcedureRepository procedureRepository,
                           CategoryRepository categoryRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.procedureRepository = procedureRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }
    public Optional<Cart> findByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public Cart addProcedureToCart(Long userId, Long procedureId) throws ProcedureNotFoundException {
        // Получить пользователя и процедуру из базы данных
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        Procedure procedure = procedureRepository.findById(procedureId)
                .orElseThrow(() -> new ProcedureNotFoundException("Procedure not found with id: " + procedureId));

        // Получить корзину пользователя или создать новую, если ее нет
        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart(user));

        // Проверить, есть ли уже эта процедура в корзине
        if (!cart.getProcedures().contains(procedure)) {
            cart.getProcedures().add(procedure);
        }

        // Сохранить обновленную корзину в базе данных
        return cartRepository.save(cart);
    }

    public Cart removeProcedureFromCart(Long userId, Long procedureId) throws ProcedureNotFoundException {
        // Получить пользователя и процедуру из базы данных
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        Procedure procedure = procedureRepository.findById(procedureId)
                .orElseThrow(() -> new ProcedureNotFoundException("Procedure not found with id: " + procedureId));

        // Получить корзину пользователя или бросить исключение, если ее нет
        Cart cart = (Cart) cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user with id: " + userId));

        // Удалить процедуру из корзины, если она там есть
        cart.getProcedures().remove(procedure);

        // Сохранить обновленную корзину в базе данных
        return cartRepository.save(cart);
    }

    public void checkoutCart(Long userId) throws EmptyCartException {
        // Получить корзину пользователя или бросить исключение, если она пуста
        Cart cart = (Cart) cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartNotFoundException("Cart not found for user with id: " + userId));

        // Проверить, что корзина не пуста перед оформлением заказа
        if (cart.getProcedures().isEmpty()) {
            throw new EmptyCartException("Cannot checkout an empty cart");
        }

        // Ваша логика для оформления заказа, например, создание заказа в базе данных и очистка корзины
        // ...

        // Очистить корзину после оформления заказа
        cart.getProcedures().clear();
        cartRepository.save(cart);
    }
}