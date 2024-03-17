package com.example.end.mapping;

import com.example.end.models.Procedure;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProcedureMapper {

    ProcedureMapper toDto(Procedure procedure);

    Procedure toEntity(ProcedureMapper procedureDto);
}
