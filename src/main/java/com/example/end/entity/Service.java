package com.example.end.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category Category;
}
//Сущность Service может быть полезной, если у вас есть необходимость представлять конкретные услуги,
// связанные с определенной категорией красоты. Предположим, у вас есть категория "Маникюр", и
// вы хотите предоставить различные виды маникюра в рамках этой категории.
// Сущность Service может представлять собой
// конкретные виды услуг, такие как "Маникюр классический", "Маникюр гель-лаком", "Наращивание ногтей" и т. д.