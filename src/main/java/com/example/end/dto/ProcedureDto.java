package com.example.end.dto;

import com.example.end.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcedureDto {
    private Long id;

    @NotBlank(message = "Procedure name is required")
    private String name;

    @NotNull(message = "Price is required")
    private BigDecimal price;

    private boolean isActive;

    private Category categories;
}
