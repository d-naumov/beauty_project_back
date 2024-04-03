package com.example.end.controller;


import com.example.end.controller.api.ProcedureApi;
import com.example.end.dto.ProcedureDto;
import com.example.end.service.interfaces.ProcedureService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class ProcedureController implements ProcedureApi {

  private final ProcedureService procedureService;
  @Override
  public ProcedureDto createProcedure(ProcedureDto procedureDto) {
    return procedureService.createProcedure(procedureDto);
  }
  @Override
  public void update(ProcedureDto procedure) {
  procedureService.update(procedure);
  }
  @Override
  public ProcedureDto deleteById(Long id) {
    return procedureService.deleteById(id);
  }
  @Override
  public ProcedureDto findByName(String name) {
    return procedureService.findByName(name);
  }
  @Override
  public List<ProcedureDto> findAll() {
  return procedureService.findAll();
  }

  @Override
  public ProcedureDto findById(Long id) {
    return procedureService.findById(id);
  }
}


