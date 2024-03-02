package com.example.end.service;

import com.example.end.dto.MasterDto;
import com.example.end.entity.Master;
import com.example.end.repository.interfaces.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterService {

  private MasterRepository masterRepository;

  @Autowired
  public MasterService(MasterRepository masterRepository) {
    this.masterRepository = masterRepository;
  }

  public Master getMasterById(Long masterId) {
    // Логика для получения мастера по ID
    return masterRepository.findById(masterId).orElse(null);
  }

  public Master updateMasterProfile(Long masterId, MasterDto masterDto) {
    // Логика для обновления профиля мастера
    Master existingMaster = masterRepository.findById(masterId).orElseThrow(() -> new RuntimeException("Мастер не найден"));

    // Обновление данных мастера
    existingMaster.setFirstName(masterDto.getFirstName());
    existingMaster.setLastName(masterDto.getLastName());
    // Добавьте другие поля профиля мастера, которые нужно обновить

    return masterRepository.save(existingMaster);
  }
}
