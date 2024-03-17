package com.example.end.dto;


import com.example.end.models.Procedure;
import com.example.end.models.User;
import lombok.*;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Long id;

    private User user;

    private List<Procedure> procedures;

}

