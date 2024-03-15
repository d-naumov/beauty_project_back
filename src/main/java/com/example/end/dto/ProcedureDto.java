package com.example.end.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ProcedureDto {
    @NotBlank(message = "Procedure name is required")
    private String name;

    @NotNull(message = "Price is required")
    private BigDecimal price;

    public ProcedureDto() {
    }
}
