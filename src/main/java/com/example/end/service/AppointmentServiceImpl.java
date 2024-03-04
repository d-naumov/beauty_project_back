package com.example.end.service;

import com.example.end.entity.Appointment;
import com.example.end.entity.Procedure;
import com.example.end.entity.User;
import com.example.end.repository.interfaces.AppointmentRepository;
import com.example.end.service.interfaces.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment createAppointment(User user, Procedure procedure) {
        Appointment appointment = new Appointment();
        appointment.setUser(user);
        appointment.setProcedure(procedure);
        // Другая логика по созданию записи

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsForUser(User user) {
        return appointmentRepository.findByUser(user);
    }

    // Другие методы реализации
}

