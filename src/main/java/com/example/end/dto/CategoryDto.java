package com.example.end.dto;

import com.example.end.models.Procedure;
import com.example.end.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {


    private Long id;

    private String name;

    private Set<Procedure> procedures ;


    private Set<User> userMaster ;
}
