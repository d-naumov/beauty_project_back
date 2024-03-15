package com.example.end.dto;


import com.example.end.models.Procedure;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@Data
public class CartDto {

    private List<Procedure> selectedProcedures;
    private String date;
    private String time;
    private BigDecimal totalCost;

}

