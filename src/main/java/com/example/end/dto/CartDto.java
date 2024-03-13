package com.example.end.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

import com.example.end.models.Procedure;

@Setter
@Getter
@Data
public class CartDto {

    private List<Procedure> selectedProcedures;
    private String date;
    private String time;
    private BigDecimal totalCost;

}


