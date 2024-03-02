package com.example.end.controller;


import com.example.end.entity.Appointment;
import com.example.end.service.interfaces.AppointmentService;
import com.example.end.service.interfaces.UserService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

  private AppointmentService appointmentService;
  private UserService userService;

  // Конструктор и инъекция зависимостей AppointmentService и UserService

  @PostMapping
  public ResponseEntity<Appointment> createAppointment(@RequestParam String username, @RequestParam Long procedureId) {
    return null;
    // Метод для создания записи на процедуру
  }

  @GetMapping("/user/{username}")
  public ResponseEntity<List<Appointment>> getAppointmentsForUser(@PathVariable String username) {
    return null;
    // Метод для получения списка записей на процедуры для пользователя
  }

  // Другие методы
}
