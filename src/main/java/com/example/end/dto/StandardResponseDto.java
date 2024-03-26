package com.example.end.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Message", description = "Какое-либо сообщение с сервера")
public class StandardResponseDto {
    @Schema(description = "Возможно: сообщение об ошибке, об изменении состояния и т.д.", example = "Не найдена категория")
    private String message;
}
