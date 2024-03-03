package com.example.end.service.interfaces;

import com.example.end.entity.Procedure;
import java.util.List;

public interface ProcedureService {

  Procedure createProcedure(String name, double price);

  List<Procedure> getAllProcedures();

    Procedure getProcedureById(Long procedureId);
    // Другие методы
}

