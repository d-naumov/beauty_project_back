package com.example.end.service;

import com.example.end.dto.MasterDto;
import com.example.end.entity.Master;
import com.example.end.repository.interfaces.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterService {

  private final MasterRepository masterRepository;

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
  public MasterDto getMasterProfile(Long masterId) {
    // Логика для получения профиля мастера по его идентификатору
    Master master = masterRepository.findById(masterId)
            .orElseThrow(() -> new RuntimeException("Мастер не найден по идентификатору: " + masterId));
    // Здесь вы можете выполнить маппинг между сущностью Master и DTO MasterDto
    // Предположим, что у вас есть метод mapToDto в вашем классе-маппере или используйте ModelMapper
    MasterDto masterDto = mapToDto(master);

    return masterDto;
  }

  private MasterDto mapToDto(Master master) {
    MasterDto masterDto = new MasterDto();
    masterDto.setId(master.getId());
    masterDto.setFirstName(master.getFirstName());
    masterDto.setLastName(master.getLastName());
    masterDto.setSpecialization(master.getSpecialization());
    //  другие поля, которые нужно перенести

    return masterDto;
  }


}
