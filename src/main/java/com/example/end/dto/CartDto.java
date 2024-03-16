package com.example.end.dto;


import com.example.end.models.Procedure;
import com.example.end.models.User;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long id;

    private User user;

    private List<Procedure> procedures;

}

