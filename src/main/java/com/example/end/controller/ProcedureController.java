package com.example.end.controller;

import com.example.end.models.Procedure;
import com.example.end.service.interfaces.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

@GetMapping("/{id}")
public ResponseEntity<?> getProcedureById(@PathVariable Long id) {
  // Логика для получения процедуры по идентификатору
  Optional<Procedure> procedure = Optional.ofNullable(procedureService.getProcedureById(id));

  // Проверка, была ли найдена процедура
  if (procedure.isPresent()) {
    // Возвращение ответа с найденной процедурой
    return ResponseEntity.ok(procedure);
  } else {
    // Возвращение ответа с кодом 404 (Not Found) и пользовательским сообщением
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Процедура с id " + id + " не найдена");
  }
}
  @PutMapping("/{id}")
  public ResponseEntity<Procedure> updateProcedure(@PathVariable Long id, @RequestBody Procedure updatedProcedure) {
    // Логика для обновления процедуры по идентификатору
    Procedure updated = procedureService.updateProcedure (id, updatedProcedure);

    // Проверка, была ли найдена и обновлена процедура
    if (updated != null) {
      // Возвращение ответа с обновленной процедурой
      return ResponseEntity.ok(updated);
    } else {
      // Возвращение ответа с кодом 404 (Not Found), если процедура не найдена
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProcedure(@PathVariable Long id) {
    // Логика для удаления процедуры по идентификатору
    boolean deleted = procedureService.deleteProcedure(id);

    // Проверка, была ли найдена и удалена процедура
    if (deleted) {
      // Возвращение ответа с кодом 204 (No Content) в случае успешного удаления
      return ResponseEntity.noContent().build();
    } else {
      // Возвращение ответа с кодом 404 (Not Found), если процедура не найдена
      return ResponseEntity.notFound().build();
    }
  }
}



