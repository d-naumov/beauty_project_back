package com.example.end.models;

import lombok.*;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rating")
    private int rating;

    @Column(name = "comment")
    private String comment;


}
