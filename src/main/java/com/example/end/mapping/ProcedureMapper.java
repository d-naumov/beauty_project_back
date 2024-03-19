package com.example.end.mapping;

import com.example.end.dto.ProcedureDto;
import com.example.end.models.Procedure;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProcedureMapper {

    ProcedureDto toDto(Procedure procedure);

    Procedure toEntity(ProcedureDto procedureDto);
}
