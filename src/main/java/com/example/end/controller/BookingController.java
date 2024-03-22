package com.example.end.controller;


import com.example.end.dto.BookingDto;
import com.example.end.dto.UserDto;
import com.example.end.mapping.UserMapper;
import com.example.end.models.User;
import com.example.end.service.interfaces.BookingService;
import com.example.end.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final UserServiceImpl userService;
    private final UserMapper userMapper;

    @Autowired
    public BookingController(BookingService bookingService, UserServiceImpl userService, UserMapper userMapper) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.userMapper = userMapper;
    }


    @PostMapping("/create-booking")
    public ResponseEntity<BookingDto> createBooking(@RequestBody UserDto userDto, @RequestParam Long procedureId) {
        // Получение объекта Optional<User> по его идентификатору
        Optional<User> userOptional = userService.findById(userDto.getId());

        // Проверка, найден ли пользователь
        if (userOptional.isPresent()) {
            // Извлечение объекта пользователя из Optional
            User user = userOptional.get();

            // Создание объекта BookingDto с информацией о бронировании
            BookingDto bookingDto = new BookingDto();
            bookingDto.setDateTime(LocalDateTime.now());
            bookingDto.setUserId(user.getId());
            bookingDto.setProcedureId(procedureId);

            // Вызов метода createBooking сервиса, чтобы создать новое бронирование
            BookingDto createdBooking = bookingService.createBooking(bookingDto, user.getId(), procedureId);

            // Проверка, создано ли бронирование успешно
            if (createdBooking != null) {
                return ResponseEntity.ok(createdBooking); // Возвращаем успешно созданное бронирование
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Если произошла ошибка при создании бронирования
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Если пользователь не найден
        }
    }

    @GetMapping("/master/{masterId}")
    public ResponseEntity<List<BookingDto>> getMasterBookings(@PathVariable Long masterId) {
        // Преобразуем идентификатор мастера в объект User
        User master = userService.findById(masterId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Преобразуем объект User в объект UserDto
        UserDto masterDto = userMapper.toDto(master);

        // Получаем список бронирований для мастера
        List<BookingDto> masterBookings = bookingService.getMasterBookings(masterDto);

        // Возвращаем список бронирований в теле ответа
        return ResponseEntity.ok(masterBookings);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingDto>> getUserBookings(@PathVariable Long userId) {
        List<BookingDto> userBookings = bookingService.getUserBookings(userId);
        return ResponseEntity.ok(userBookings);
    }

    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long bookingId) {
        // Вызов метода сервиса для отмены бронирования
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok("Booking canceled successfully");
    }

}