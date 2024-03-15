package com.example.end.service.interfaces;

import com.example.end.models.Procedure;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ProcedureService {
  Procedure createProcedure(String name, double price);

  List<Procedure> getAllProcedures();

  boolean deleteProcedure(Long id);


  Procedure updateProcedure(Long id, Procedure updatedProcedure);

  Procedure findById(Long procedureId);


  Procedure getProcedureById(Long id);

  Procedure createProcedure(Procedure procedure);

  Set<Procedure> getProceduresById(Set<Long> procedureIds);

}

