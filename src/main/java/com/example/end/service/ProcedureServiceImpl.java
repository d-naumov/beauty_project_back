package com.example.end.service;


import com.example.end.dto.ProcedureDto;
import com.example.end.mapping.ProcedureMapper;
import com.example.end.models.Procedure;
import com.example.end.repository.ProcedureRepository;
import com.example.end.service.interfaces.ProcedureService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@Service
public class ProcedureServiceImpl implements ProcedureService {

    private final ProcedureRepository procedureRepository;
    private final ProcedureMapper procedureMapper;


    // Метод для создания новой процедуры на основе DTO
    @Override
    public ProcedureDto createProcedure(ProcedureDto procedureDto) {
        // Преобразование DTO в сущность процедуры
        Procedure procedure = procedureMapper.toEntity(procedureDto);
        // Сохранение процедуры в репозитории
        Procedure createdProcedure = procedureRepository.save(procedure);
        // Преобразование созданной процедуры обратно в DTO и возврат ее
        return procedureMapper.toDto(createdProcedure);
    }

    // Метод для получения списка всех процедур

    @Override
    public List<Procedure> getAllProcedures() {
        return procedureRepository.findAll();
    }


    // Метод для удаления процедуры по ее идентификатору
    @Override
    public boolean deleteProcedure(Long id) {
        // Проверка существования процедуры с указанным идентификатором
        if (procedureRepository.existsById(id)) {
            // Удаление процедуры из репозитория
            procedureRepository.deleteById(id);
            // Возвращение true, если удаление прошло успешно
            return true;
        }
        // Возвращение false, если процедура не была найдена
        return false;
    }

    // Метод для обновления процедуры по ее идентификатору и преобразования DTO
    @Override
    public ProcedureDto updateProcedure(Long id, Procedure updatedProcedure) {
        // Поиск существующей процедуры по ее идентификатору
        Procedure existingProcedure = procedureRepository.findById(id).orElse(null);
        // Проверка существования процедуры
        if (existingProcedure != null) {
            // Обновление данных существующей процедуры
            existingProcedure.setName(updatedProcedure.getName());
            existingProcedure.setPrice(updatedProcedure.getPrice());
            existingProcedure.setActive(updatedProcedure.isActive());
            // Сохранение обновленной процедуры в репозитории
            Procedure savedProcedure = procedureRepository.save(existingProcedure);
            // Преобразование обновленной процедуры обратно в DTO и возврат ее
            return procedureMapper.toDto(savedProcedure);
        }
        // Возвращение null, если процедура не была найдена
        return null;
    }

    // Метод для получения процедуры по ее идентификатору и преобразования ее в DTO
    @Override
    public ProcedureDto getProcedureById(Long id) {
        // Поиск процедуры по ее идентификатору
        Procedure procedure = procedureRepository.findById(id).orElse(null);
        // Проверка существования процедуры
        if (procedure != null) {
            // Преобразование процедуры в DTO и возврат ее
            return procedureMapper.toDto(procedure);
        } else {
            // Возвращение null, если процедура не была найдена
            return null;
        }
    }

    // Метод для получения набора процедур по их идентификаторам и преобразования их в DTO
    @Override
    public Set<ProcedureDto> getProceduresById(Set<Long> procedureIds) {
        // Получение списка процедур по их идентификаторам из репозитория
        List<Procedure> proceduresList = procedureRepository.findAllById(procedureIds);

        // Преобразование списка в HashSet для удаления дубликатов и уникальности элементов
        Set<Procedure> proceduresSet = new HashSet<>(proceduresList);

        // Преобразование набора процедур в набор DTO с помощью стримов
        return proceduresSet.stream()
                .map(procedureMapper::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Procedure findById(Long procedureId) {
        return procedureRepository.findById(procedureId).orElse(null);
    }


}