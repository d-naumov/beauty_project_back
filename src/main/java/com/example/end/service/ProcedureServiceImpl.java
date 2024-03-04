package com.example.end.service;

import com.example.end.entity.Procedure;
import com.example.end.repository.interfaces.ProcedureRepository;
import com.example.end.service.interfaces.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcedureServiceImpl implements ProcedureService {

    private ProcedureRepository procedureRepository; // Предположим, что у вас есть репозиторий для работы с сущностью Procedure

    @Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository) {
        this.procedureRepository = procedureRepository;
    }

    @Override
    public Procedure createProcedure(String name, double price) {
        Procedure procedure = new Procedure();
        procedure.setName(name);
        procedure.setPrice(price);
        return procedureRepository.save(procedure);
    }

    @Override
    public List<Procedure> getAllProcedures() {
        return procedureRepository.findAll();
    }

    @Override
    public Procedure getProcedureById(Long procedureId) {
        return procedureRepository.findById(procedureId).orElse(null);
    }

    // Другие методы, если необходимо
}

