package com.example.end.controller;


import com.example.end.entity.Appointment;
import com.example.end.entity.Procedure;
import com.example.end.entity.User;
import com.example.end.service.interfaces.AppointmentService;
import com.example.end.service.interfaces.ProcedureService;
import com.example.end.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

  private final AppointmentService appointmentService;
  private final UserService userService;
  private final ProcedureService procedureService;

  @Autowired
  public AppointmentController(AppointmentService appointmentService, UserService userService, ProcedureService procedureService) {
    this.appointmentService = appointmentService;
    this.userService = userService;
      this.procedureService = procedureService;
  }

  @PostMapping
  public ResponseEntity<Appointment> createAppointment(@RequestParam String username, @RequestParam Long procedureId) {
    // метод в AppointmentService для создания записи
    User user = userService.getUserByUsername(username);
    Procedure procedure = procedureService.getProcedureById(procedureId);

    if (user != null && procedure != null) {
      Appointment newAppointment = appointmentService.createAppointment(user, procedure);
      return ResponseEntity.ok(newAppointment);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("/user/{username}")
  public ResponseEntity<List<Appointment>> getAppointmentsForUser(@PathVariable String username) {
    User user = userService.getUserByUsername(username);
    if (user != null) {
      List<Appointment> appointments = appointmentService.getAppointmentsForUser(user);
      return ResponseEntity.ok(appointments);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
}


