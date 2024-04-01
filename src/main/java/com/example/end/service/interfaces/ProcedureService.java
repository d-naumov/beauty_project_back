package com.example.end.service.interfaces;

import com.example.end.dto.ProcedureDto;
import com.example.end.models.Procedure;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ProcedureService {
  ProcedureDto createProcedure(ProcedureDto procedureDto);
  boolean deleteProcedure(Long id);
  List<Procedure> getAllProcedures();
  ProcedureDto updateProcedure(Long id, Procedure updatedProcedure);

  ProcedureDto getProcedureById(Long id);

  Set<ProcedureDto> getProceduresById(Set<Long> procedureIds);

  Procedure findById(Long procedureId);

}