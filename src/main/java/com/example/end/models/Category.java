package com.example.end.models;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "category_procedures",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "procedure_id")
    )
    private Set<Procedure> procedures = new HashSet<>();

    @ManyToMany(mappedBy = "categories")
    private Set<User> users = new HashSet<>();

}

