package com.example.end.repository.interfaces;

import com.example.end.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends JpaRepository<Master, Long> {
  // Здесь кастомные методы для работы с мастерами
}

