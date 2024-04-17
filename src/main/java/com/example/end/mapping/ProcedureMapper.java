package com.example.end.mapping;

import com.example.end.dto.ProcedureDto;
import com.example.end.models.Procedure;
import org.springframework.stereotype.Service;



@Service
public class ProcedureMapper {

   public ProcedureDto toDto(Procedure procedure){
        return ProcedureDto.builder()
                .id(procedure.getId())
                .price(procedure.getPrice())
                .name(procedure.getName())
                .build();
    }

    public Procedure toEntity(ProcedureDto procedureDto){
        return Procedure.builder()
                .id(procedureDto.getId())
                .price(procedureDto.getPrice())
                .name(procedureDto.getName())
                .build();

}
}

