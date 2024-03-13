package com.example.end.service.interfaces;

import com.example.end.models.Procedure;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ProcedureService {
  Procedure createProcedure(String name, double price);

  List<Procedure> getAllProcedures();

  boolean deleteProcedure(int id);


  Procedure updateProcedure(int id, Procedure updatedProcedure);

  Procedure findById(int procedureId);


  Procedure getProcedureById(int id);

  Procedure createProcedure(Procedure procedure);

  void deleteProcedure(Integer id);

  Set<Procedure> getProceduresByIds(Set<Integer> procedureIds);

}

