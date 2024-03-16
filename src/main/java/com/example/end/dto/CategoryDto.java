package com.example.end.dto;

import com.example.end.models.Procedure;
import com.example.end.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
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
