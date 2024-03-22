package com.example.end.mapping;

import com.example.end.dto.ProcedureDto;
import com.example.end.models.Procedure;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProcedureMapper {

    ProcedureDto toDto(Procedure procedure);

    Procedure toEntity(ProcedureDto procedureDto);
    List<ProcedureDto> toDtoList(List<Procedure> categories);
    // Добавление метода для преобразования списка объектов
    // Category в список объектов CategoryDto
}

