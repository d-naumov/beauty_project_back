package com.example.end.service.interfaces;

import com.example.end.models.Procedure;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProcedureService {

  Procedure createProcedure(String name, double price);

  List<Procedure> getAllProcedures();


    Procedure getProcedureById(Long procedureId);
    // Другие методы
}

