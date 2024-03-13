package com.example.end.service;

import com.example.end.models.Procedure;
import com.example.end.repository.ProcedureRepository;
import com.example.end.service.interfaces.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;


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
    public boolean deleteProcedure(int id) {
        return false;
    }

    @Override
    public Procedure updateProcedure(int id, Procedure updatedProcedure) {
        return null;
    }

    @Override
    public Procedure findById(int procedureId) {
        return null;
    }

    @Override
    public Procedure getProcedureById(int procedureId) {
        return procedureRepository.findById(procedureId).orElse(null);
    }

    @Override
    public Procedure createProcedure(Procedure procedure) {
        return null;
    }

    @Override
    public void deleteProcedure(Integer id) {

    }

    @Override
    public Set<Procedure> getProceduresByIds(Set<Integer> procedureIds) {
        return null;
    }


}

