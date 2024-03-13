package com.example.end.models;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;
@Data
@Entity
@Table(name = "procedures")
public class Procedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @Pattern(regexp = "[A-Z][a-z]{3,}")
    private String name;

    @Column(name = "price")
    @Max(90000)
    @Min(10)
    private double price;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToMany(mappedBy = "procedures")
    private Set<Category> categories = new HashSet<>();

    @ManyToMany(mappedBy = "procedures")
    private Set<User> userMaster = new HashSet<>();
//    @ManyToMany(mappedBy = "procedures")
//    private Set<Procedure> procedures = new HashSet<>();

    public Procedure() {

    }
}
