package com.example.end.repository.interfaces;



import com.example.end.entity.Appointment;
import com.example.end.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
  List<Appointment> findByUser(User user);

}
