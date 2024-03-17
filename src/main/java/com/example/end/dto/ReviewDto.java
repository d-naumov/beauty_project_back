package com.example.end.dto;

import com.example.end.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {


    private Long id;

    private User user;

    private String content;

    private int rating;
}
