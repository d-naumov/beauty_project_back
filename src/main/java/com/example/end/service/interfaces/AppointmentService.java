package com.example.end.service.interfaces;


import com.example.end.entity.Appointment;
import com.example.end.entity.Procedure;
import com.example.end.entity.User;
import java.util.List;

public interface AppointmentService {

  Appointment createAppointment(User user, Procedure procedure);

  List<Appointment> getAppointmentsForUser(User user);
  // Другие методы
}

