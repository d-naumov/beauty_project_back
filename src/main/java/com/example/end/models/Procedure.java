package com.example.end.models;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "procedures")
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Pattern(regexp = "[A-Z][a-z]{3,}")
    private String name;

    @Column(name = "price")
    @Max(90000)
    @Min(10)
    private double price;

    @Column(name = "is_active")
    private boolean isActive;

    public Procedure(Long id, String name, double price, boolean isActive) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isActive = isActive;
    }

    public Procedure() {

    }
}

