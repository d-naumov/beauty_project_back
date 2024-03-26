package com.example.end.controller;


import com.example.end.dto.ProcedureDto;
import com.example.end.mapping.ProcedureMapper;
import com.example.end.models.Procedure;
import com.example.end.service.interfaces.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/procedures")
public class ProcedureController {

  private final ProcedureService procedureService;
  private final ProcedureMapper procedureMapper;

  @Autowired
  public ProcedureController(ProcedureService procedureService, ProcedureMapper procedureMapper) {
    this.procedureService = procedureService;
    this.procedureMapper = procedureMapper;
  }

  @PostMapping
  public ResponseEntity<ProcedureDto> createProcedure(@RequestBody ProcedureDto procedureDto) {
    ProcedureDto createdProcedureDto = procedureService.createProcedure(procedureDto);
    return ResponseEntity.ok(createdProcedureDto);
  }

  // Получение списка всех процедур
  @GetMapping
  public ResponseEntity<List<ProcedureDto>> getAllProcedures() {
    // Получение списка всех процедур из сервиса
    List<Procedure> procedures = procedureService.getAllProcedures();
    // Преобразование списка процедур в список DTO
    List<ProcedureDto> procedureDtos = procedures.stream()
            .map(procedureMapper::toDto) // Преобразование Procedure в ProcedureDto
            .collect(Collectors.toList());
    // Возвращение списка DTO процедур
    return ResponseEntity.ok(procedureDtos);
  }


  // Обновление процедуры по идентификатору
  @PutMapping("/{id}")
  public ResponseEntity<ProcedureDto> updateProcedure(@PathVariable Long id, @RequestBody ProcedureDto updatedProcedureDto) {
    // Преобразование DTO в сущность Procedure
    Procedure updatedProcedureEntity = procedureMapper.toEntity(updatedProcedureDto);
    // Вызов метода updateProcedure вашего сервиса с сущностью Procedure
    ProcedureDto updatedProcedure = procedureService.updateProcedure(id, updatedProcedureEntity);
    if (updatedProcedure != null) {
      return ResponseEntity.ok(updatedProcedure);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // Обновление процедуры по идентификатору
  @PutMapping("/update/{id}")
  public ResponseEntity<ProcedureDto> updateProcedureById(@PathVariable Long id, @RequestBody ProcedureDto updatedProcedureDto) {
    // Преобразование DTO в сущность Procedure
    Procedure updatedProcedureEntity = procedureMapper.toEntity(updatedProcedureDto);
    // Вызов метода updateProcedure вашего сервиса с сущностью Procedure
    ProcedureDto updatedProcedureDtoResult = procedureService.updateProcedure(id, updatedProcedureEntity);
    if (updatedProcedureDtoResult != null) {
      return ResponseEntity.ok(updatedProcedureDtoResult);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // Удаление процедуры по идентификатору
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProcedure(@PathVariable Long id) {
    boolean deleted = procedureService.deleteProcedure(id);
    if (deleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}



