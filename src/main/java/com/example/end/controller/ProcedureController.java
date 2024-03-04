package com.example.end.controller;

import com.example.end.entity.Procedure;
import com.example.end.service.interfaces.ProcedureService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/procedures")
public class ProcedureController {

  private  ProcedureService procedureService;

  // Конструктор и инъекция зависимости ProcedureService

  @PostMapping
  public ResponseEntity<Procedure> createProcedure(@RequestParam String name, @RequestParam double price) {
    return null;
    // Метод для создания новой процедуры
  }

  @GetMapping
  public ResponseEntity<List<Procedure>> getAllProcedures() {
    return null;
    // Метод для получения списка всех процедур
  }


}

