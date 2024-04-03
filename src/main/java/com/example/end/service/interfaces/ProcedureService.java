package com.example.end.service.interfaces;

import com.example.end.dto.ProcedureDto;
import com.example.end.models.Procedure;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ProcedureService {


  ProcedureDto createProcedure(ProcedureDto procedureDto);

  void update(ProcedureDto procedure);

  ProcedureDto deleteById(Long id);

  ProcedureDto findByName(String name);

  List<ProcedureDto> findAll();



  ProcedureDto findById(Long id);
}