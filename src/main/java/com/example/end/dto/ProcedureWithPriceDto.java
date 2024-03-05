package com.example.end.dto;

import lombok.Data;

@Data
public class ProcedureWithPriceDto {

    private String name;
    private double price;


    public ProcedureWithPriceDto() {
    }

    public ProcedureWithPriceDto(String name, double price) {
        this.name = name;
        this.price = price;
    }

}
