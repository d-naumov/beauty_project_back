package com.example.end.entity;


import lombok.Data;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    //    @OneToMany(mappedBy = "category")
//    private Set<Procedure> procedures;
    @ManyToMany
    @JoinTable(
            name = "master_service_mapping",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "procedure_id")
    )
    private Set<Procedure> procedures = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Service> services;

    @ManyToMany(mappedBy = "categories")
    private Set<Master> masters;

    public Category() {
    }
}


