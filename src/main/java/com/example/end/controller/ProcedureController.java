package com.example.end.controller;

import com.example.end.models.Procedure;
import com.example.end.service.interfaces.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/procedures")
public class ProcedureController {

  private final ProcedureService procedureService;


  @Autowired
  public ProcedureController(ProcedureService procedureService) {
    this.procedureService = procedureService;
  }

  @PostMapping
  public ResponseEntity<Procedure> createProcedure(@RequestParam String name, @RequestParam double price) {
    // Создание новой процедуры
    Procedure newProcedure = procedureService.createProcedure(name, price);

    // Возвращение ответа с созданной процедурой
    return ResponseEntity.ok(newProcedure);
  }

  @GetMapping
  public ResponseEntity<List<Procedure>> getAllProcedures() {
    // Логика для получения списка всех процедур
    List<Procedure> procedures = procedureService.getAllProcedures();

    // Возвращение ответа со списком процедур
    return ResponseEntity.ok(procedures);
  }
}



