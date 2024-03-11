package com.example.end.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "procedure_id")
    private Procedure procedure;

    private LocalDateTime dateTime;


    public Long getProcedure_id() {
        return (procedure != null) ? procedure.getId() : null;
    }

    public void setProcedure_id(Long procedure_id) {
        // Ищем Procedure по id и устанавливаем его в поле procedure
        // (это может потребовать изменений в вашем сервисном слое)
    }

    // Другие геттеры и сеттеры
}

